package sk.umb.example.library.book.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sk.umb.example.library.book.persistence.entity.BookEntity;
import sk.umb.example.library.book.persistence.repository.BookRepository;
import sk.umb.example.library.category.persistence.entity.CategoryEntity;
import sk.umb.example.library.category.persistence.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    //? Konstruktor, inicializacia repozitaru
    private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository;
    }

    //? Get operacie
    public List<BookDetailDataTransferObject> getBooks() {
        return mapToBookDataTransferObjectList(bookRepository.findAll());
    }

    public BookDetailDataTransferObject getBookById(Long bookId) {
        return mapToDataTransferObject(getBookEntityById(bookId));
    }

    public BookEntity getBookEntityById(Long bookId) {
        Optional<BookEntity> bookEntity = bookRepository.findById(bookId);
		// ERROR: Chybná podmienka
        if (bookEntity.isEmpty()) {
            throw new IllegalArgumentException("Couldn't find book with ID: " + bookId);
        }

        return bookEntity.get();
    }

    //? Post, Update, Delete
    @Transactional
    public Long createBook(BookRequestDataTransferObject book) {
        BookEntity bookEntity = mapToEntity(book);

        return bookRepository.save(bookEntity).getId();
    }

	// ERROR: Chýba zmena údajov == update
    @Transactional
    public void updateBook(Long bookId, BookRequestDataTransferObject book) {
        BookEntity bookEntity = getBookEntityById(bookId);

		bookEntity.setCategoryIds(mapToCategoryIds(book));
		bookEntity.setAuthorFirstName(book.getAuthorFirstName());
		bookEntity.setAuthorLastName(book.getAuthorLastName());
		bookEntity.setBookCount(book.getBookCount());
		bookEntity.setIsbn(book.getIsbn());
		bookEntity.setTitle(book.getTitle());

		bookRepository.save(bookEntity);
    }

    @Transactional
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    
    //? Mapping
    private BookEntity mapToEntity(BookRequestDataTransferObject book) {
        BookEntity bookEntity = new BookEntity();

        bookEntity.setAuthorFirstName(book.getAuthorFirstName());
        bookEntity.setAuthorLastName(book.getAuthorLastName());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setBookCount(book.getBookCount());
        bookEntity.setCategoryIds(mapToCategoryIds(book));

        return bookEntity;
    }

	private List<Long> mapToCategoryIds(BookRequestDataTransferObject book) {
		List<Long> categoryIds = new ArrayList<>();

		if (!Objects.isNull(book.getCategories())) {
			book.getCategories().forEach(categoryId -> {
				Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);
				
				if (categoryEntity.isPresent()) {
					categoryIds.add(categoryEntity.get().getId());
				}
			});
		}

		return categoryIds;
	}

    private BookDetailDataTransferObject mapToDataTransferObject(BookEntity bookEntity) {
        BookDetailDataTransferObject book = new BookDetailDataTransferObject();

        book.setId(bookEntity.getId());
        book.setAuthorFirstName(bookEntity.getAuthorFirstName());
        book.setAuthorLastName(bookEntity.getAuthorLastName());
        book.setTitle(bookEntity.getTitle());
        book.setIsbn(bookEntity.getIsbn());
        book.setBookCount(bookEntity.getBookCount());
		book.setCategories(bookEntity.getCategoryIds());

        return book;
    }
    
    private List<BookDetailDataTransferObject> mapToBookDataTransferObjectList(Iterable<BookEntity> bookEntities) {
        List<BookDetailDataTransferObject> books = new ArrayList<>();

        bookEntities.forEach(categoryEntity -> {
            BookDetailDataTransferObject bookDetailDataTransferObject = mapToDataTransferObject(categoryEntity);
            books.add(bookDetailDataTransferObject);
        });

        return books;
    }
    
}
