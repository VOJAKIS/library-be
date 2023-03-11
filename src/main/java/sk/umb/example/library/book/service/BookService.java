package sk.umb.example.library.book.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sk.umb.example.library.book.persistence.entity.BookEntity;
import sk.umb.example.library.book.persistence.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    //? Konstruktor, inicializacia repozitaru
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
        if(!bookEntity.isEmpty()) {
            throw new IllegalArgumentException("Couldn't find book with ID:" + bookId);
        }

        return bookEntity.get();
    }


    //? Post, Update, Delete
    @Transactional
    public Long createBook(BookRequestDataTransferObject book) {
        BookEntity bookEntity = mapToEntity(book);

        return bookRepository.save(bookEntity).getId();
    }

    @Transactional
    public void updateBook(Long bookId, BookRequestDataTransferObject book) {
        BookEntity bookEntity = getBookEntityById(bookId);
        // TODO: Pridat validaciu ze je aktualizovany subor spravny
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
        bookEntity.setCategoryIds(book.getCategoryIds());

        return bookEntity;
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
    
    private List<BookDetailDataTransferObject> mapToBookDataTransferObjectList(Iterable<BookEntity> bookEntities) {
        List<BookDetailDataTransferObject> books = new ArrayList<>();

        bookEntities.forEach(categoryEntity -> {
            BookDetailDataTransferObject bookDDTO = mapToDataTransferObject(categoryEntity);
            books.add(bookDDTO);
        });

        return books;
    }
    
}
