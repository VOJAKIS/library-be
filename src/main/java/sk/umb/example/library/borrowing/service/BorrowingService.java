package sk.umb.example.library.borrowing.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sk.umb.example.library.book.persistence.entity.BookEntity;
import sk.umb.example.library.book.persistence.repository.BookRepository;
import sk.umb.example.library.book.service.BookDetailDataTransferObject;
import sk.umb.example.library.borrowing.persistence.entity.BorrowingEntity;
import sk.umb.example.library.borrowing.persistence.repository.BorrowingRepository;
import sk.umb.example.library.category.persistence.entity.CategoryEntity;
import sk.umb.example.library.category.persistence.repository.CategoryRepository;
import sk.umb.example.library.category.service.CategoryDetailDataTransferObject;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;
import sk.umb.example.library.customer.persistence.repository.CustomerRepository;
import sk.umb.example.library.customer.service.CustomerDetailDataTransferObject;

@Service
public class BorrowingService {
	
	private final BorrowingRepository borrowingRepository;
	private final CustomerRepository customerRepository;
	private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;

	public BorrowingService(CustomerRepository customerRepository,
							BookRepository bookRepository,
							BorrowingRepository borrowingRepository,
							CategoryRepository categoryRepository) {
		this.customerRepository = customerRepository;
		this.bookRepository = bookRepository;
		this.borrowingRepository = borrowingRepository;
		this.categoryRepository = categoryRepository;
	}

	public List<BorrowingDetailDataTransferObject> getAllBorrowings() {
		return mapToDataTransferObjectList(borrowingRepository.findAll());
	}

	public List<BorrowingDetailDataTransferObject> searchBorrowingsByCustomerId(Long customerId) {
		return mapToDataTransferObjectList(borrowingRepository.findAllByCustomerId(customerId));
	}

	public List<BorrowingDetailDataTransferObject> searchBorrowingsByBookId(Long bookId) {
		return mapToDataTransferObjectList(borrowingRepository.findAllByBookId(bookId));
	}

	private List<BorrowingDetailDataTransferObject> mapToDataTransferObjectList(Iterable<BorrowingEntity> borrowingEntities) {
		List<BorrowingDetailDataTransferObject> borrowings = new ArrayList<>();

		if (! Objects.isNull(borrowingEntities)) {
			borrowingEntities.forEach(borrowing -> {
				borrowings.add(mapToBorrowingDetailDataTransferObject(borrowing));
			});
		}

		return borrowings;
	}

	public BorrowingDetailDataTransferObject getBorrowingById(Long borrowingId) {
		return mapToBorrowingDetailDataTransferObject(getBorrowingEntityById(borrowingId));
	}

	@Transactional
	public Long createBorrowing(BorrowingRequestDataTransferObject borrowingRequestDataTransferObject) {
		BorrowingEntity borrowingEntity = mapToEntity(borrowingRequestDataTransferObject);

		return borrowingRepository.save(borrowingEntity).getId();
	}

	@Transactional
	public void updateBorrowing(Long borrowingId, BorrowingRequestDataTransferObject borrowingRequestDataTransferObject) {
		BorrowingEntity borrowingEntity = getBorrowingEntityById(borrowingId);

		if (! Objects.isNull(borrowingRequestDataTransferObject.getCustomerId())) {
			Optional<CustomerEntity> customerEntity = customerRepository.findById(borrowingRequestDataTransferObject.getCustomerId());
			if (customerEntity.isPresent()) {
				borrowingEntity.setCustomer(customerEntity.get());
			}
		}

		if (! Objects.isNull(borrowingRequestDataTransferObject.getBookId())) {
			Optional<BookEntity> bookEntity = bookRepository.findById(borrowingRequestDataTransferObject.getBookId());

			if (bookEntity.isPresent()) {
				borrowingEntity.setBook(bookEntity.get());
			}
		}

		borrowingEntity.setDateOfBorrowing(new Date());

		borrowingRepository.save(borrowingEntity);
	}

	@Transactional
	public void deleteBorrowing(Long borrowingId) {
		borrowingRepository.deleteById(borrowingId);
	}

	private BorrowingEntity getBorrowingEntityById(Long borrowingId) {
		Optional<BorrowingEntity> borrowing = borrowingRepository.findById(borrowingId);

        if (borrowing.isEmpty()) {
            throw new IllegalArgumentException("Customer not found. ID: " + borrowingId);
        }

        return borrowing.get();
	}

	private List<BorrowingDetailDataTransferObject> mapToDataTransferObjectList(List<BorrowingEntity> borrowingEntities) {
		List<BorrowingDetailDataTransferObject> borrowingDetailDataTransferObjects = new ArrayList<>();
	
		borrowingEntities.forEach(borrowingEntity -> {
			BorrowingDetailDataTransferObject borrowingDetailDataTransferObject = mapToBorrowingDetailDataTransferObject(borrowingEntity);
			borrowingDetailDataTransferObjects.add(borrowingDetailDataTransferObject);
		});
	
		return borrowingDetailDataTransferObjects;
	}

	private BorrowingEntity mapToEntity(BorrowingRequestDataTransferObject borrowing) {
		BorrowingEntity borrowingEntity = new BorrowingEntity();

		if (! Objects.isNull(borrowing.getBookId())) {
			Optional<BookEntity> bookEntity = bookRepository.findById(borrowing.getBookId());

			if (bookEntity.isPresent()) {
				borrowingEntity.setBook(bookEntity.get());
			}
		}

		if (! Objects.isNull(borrowing.getCustomerId())) {
			Optional<CustomerEntity> customerEntity = customerRepository.findById(borrowing.getCustomerId());

			if (customerEntity.isPresent()) {
				borrowingEntity.setCustomer(customerEntity.get());
			}
		}

		borrowingEntity.setDateOfBorrowing(new Date());

		return borrowingEntity;
	}

	private BorrowingDetailDataTransferObject mapToBorrowingDetailDataTransferObject(BorrowingEntity borrowingEntity) {
        BorrowingDetailDataTransferObject borrowing = new BorrowingDetailDataTransferObject();
        
		borrowing.setId(borrowingEntity.getId());
        borrowing.setCustomer(mapToDataTransferObject(borrowingEntity.getCustomer()));
		borrowing.setBook(mapToBookDetailDataTransferObject(borrowingEntity.getBook()));
		borrowing.setDateOfBorrowing(borrowingEntity.getDateOfBorrowing());

        return borrowing;
    }

	private BookDetailDataTransferObject mapToBookDetailDataTransferObject(BookEntity bookEntity) {
		BookDetailDataTransferObject book = new BookDetailDataTransferObject();

		book.setId(bookEntity.getId());
		book.setAuthorFirstName(bookEntity.getAuthorFirstName());
		book.setAuthorLastName(bookEntity.getAuthorLastName());
		book.setTitle(bookEntity.getTitle());
		book.setIsbn(bookEntity.getIsbn());
		book.setBookCount(bookEntity.getBookCount());
		book.setCategories(mapToCategoryDetailList(bookEntity));

		return book;
	}

	private List<CategoryDetailDataTransferObject> mapToCategoryDetailList(BookEntity book) {
		List<CategoryDetailDataTransferObject> categoryIds = new ArrayList<>();

		if (!Objects.isNull(book.getCategories())) {
			book.getCategories().forEach(categoryId -> {
				Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId.getId());
				
				if (categoryEntity.isPresent()) {
					categoryIds.add(mapToCategoryDetail(categoryEntity.get()));
				}
			});
		}

		return categoryIds;
	}

	private CategoryDetailDataTransferObject mapToCategoryDetail(CategoryEntity categoryEntity) {
        CategoryDetailDataTransferObject category = new CategoryDetailDataTransferObject();

        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());
    
       return category;
    }

	/* private List<Long> mapToCategoryIds(BookEntity bookEntity) {
		List<Long> categoryIds = new ArrayList<>();

		if (!Objects.isNull(bookEntity.getCategoryIds())) {
			bookEntity.getCategoryIds().forEach(categoryId -> {
				Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId.getId());
				
				if (categoryEntity.isPresent()) {
					categoryIds.add(categoryEntity.get().getId());
				}
			});
		}

		return categoryIds;
	} */

	private CustomerDetailDataTransferObject mapToDataTransferObject(CustomerEntity customerEntity) {
        CustomerDetailDataTransferObject customer = new CustomerDetailDataTransferObject();

        customer.setId(customerEntity.getId());
        customer.setFirstName(customerEntity.getFirstName());
        customer.setLastName(customerEntity.getLastName());
        customer.setContactEmail(customerEntity.getContactEmail());

        return customer;
    }

	

}
