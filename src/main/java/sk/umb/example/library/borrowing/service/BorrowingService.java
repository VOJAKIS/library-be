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
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;
import sk.umb.example.library.customer.persistence.repository.CustomerRepository;
import sk.umb.example.library.customer.service.CustomerDetailDataTransferObject;

@Service
public class BorrowingService {
	
	public final BorrowingRepository borrowingRepository;
	public final CustomerRepository customerRepository;
	public final BookRepository bookRepository;

	public BorrowingService(CustomerRepository customerRepository,
							BookRepository bookRepository,
							BorrowingRepository borrowingRepository) {
		this.customerRepository = customerRepository;
		this.bookRepository = bookRepository;
		this.borrowingRepository = borrowingRepository;
	}

	public List<BorrowingDetailDataTransferObject> getAllBorrowings() {
		return mapToDataTransferObjectList(borrowingRepository.findAll());
	}

	public List<BorrowingDetailDataTransferObject> serachBorrowingsByBookName(String bookTitle) {
		return mapToDataTransferObjectList(borrowingRepository.findByBookTitle(bookTitle));
	}

	public BorrowingDetailDataTransferObject getBorrowingById(Long borrowingId) {
		return mapToDataTransferObject(getBorrowingEntityById(borrowingId));
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
			BorrowingDetailDataTransferObject borrowingDetailDataTransferObject = mapToDataTransferObject(borrowingEntity);
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

	private BorrowingDetailDataTransferObject mapToDataTransferObject(BorrowingEntity borrowingEntity) {
        BorrowingDetailDataTransferObject borrowing = new BorrowingDetailDataTransferObject();
        
		borrowing.setId(borrowingEntity.getId());
        borrowing.setCustomer(mapToDataTransferObject(borrowingEntity.getCustomer()));
		borrowing.setBook(mapToDataTransferObject(borrowingEntity.getBook()));
		borrowing.setDateOfBorrowing(borrowingEntity.getDateOfBorrowing());

        return borrowing;
    }

	private BookDetailDataTransferObject mapToDataTransferObject(BookEntity bookEntity) {
		BookDetailDataTransferObject book = new BookDetailDataTransferObject();

		book.setId(bookEntity.getId());
		book.setAuthorFirstName(bookEntity.getAuthorFirstName());
		book.setAuthorLastName(bookEntity.getAuthorLastName());
		book.setTitle(bookEntity.getTitle());
		book.setIsbn(bookEntity.getIsbn());
		book.setBookCount(bookEntity.getBookCount());
		book.setCategoryIds(bookEntity.getCategoryIds());

		return book;
	}

	private CustomerDetailDataTransferObject mapToDataTransferObject(CustomerEntity customerEntity) {
        CustomerDetailDataTransferObject customer = new CustomerDetailDataTransferObject();

        customer.setId(customerEntity.getId());
        customer.setFirstName(customerEntity.getFirstName());
        customer.setLastName(customerEntity.getLastName());
        customer.setContactEmail(customerEntity.getContactEmail());

        return customer;
    }

}
