package sk.umb.example.library.book.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final List<BookDetailDataTransferObject> books = new ArrayList<>();
    private Long lastIndex = 0L;

    public List<BookDetailDataTransferObject> getBooks() {
        return books;
    }

    public BookDetailDataTransferObject getBookById(Long bookId) {
        if (bookId < 0) { return null; }
        if (bookId >= lastIndex) { return null; }

        for (BookDetailDataTransferObject book : books) {
			if (book.getId().equals(bookId)) {
				return book;
			}
		}
        return new BookDetailDataTransferObject();
    }

    public Long createBook(BookRequestDataTransferObject book) {
        BookDetailDataTransferObject bookDataTransferObject = mapToBookDataTransferObject(book);
        bookDataTransferObject.setId(lastIndex);

        increaseIndexByOne();
        printLastIndex();

        books.add(bookDataTransferObject);

        return bookDataTransferObject.getId();
    }

    private void increaseIndexByOne() {
		lastIndex++;
	}
	private void printLastIndex() {
		System.out.println("Last index: " + lastIndex);
	}

    private BookDetailDataTransferObject mapToBookDataTransferObject(BookRequestDataTransferObject book) {
        BookDetailDataTransferObject bookDataTransferObject = new BookDetailDataTransferObject();

        bookDataTransferObject.setAuthorFirstName(book.getAuthorFirstName());
        bookDataTransferObject.setAuthorLastName(book.getAuthorLastName());
        bookDataTransferObject.setTitle(book.getTitle());
        bookDataTransferObject.setIsbn(book.getIsbn());
        bookDataTransferObject.setBookCount(book.getBookCount());
        bookDataTransferObject.setCategoryIds(book.getCategoryIds());

        return bookDataTransferObject;
    }

    public void updateBook(Long bookId, BookRequestDataTransferObject book) {
        if (bookId < 0) { return; }
		if (bookId >= lastIndex) { return; }

        for(BookDetailDataTransferObject bookDataTransferObject : books) {
            if(bookDataTransferObject.getId().equals(bookId)) {
                bookDataTransferObject.setAuthorFirstName(book.getAuthorFirstName());
                bookDataTransferObject.setAuthorLastName(book.getAuthorLastName());
                bookDataTransferObject.setTitle(book.getTitle());
                bookDataTransferObject.setIsbn(book.getIsbn());
                bookDataTransferObject.setBookCount(book.getBookCount());
                bookDataTransferObject.setCategoryIds(book.getCategoryIds());
                return;
            }
        }
    }

    public void deleteBook(Long bookId) {
        if (bookId < 0) { return; }
		if (bookId >= lastIndex) { return; }

        for (BookDetailDataTransferObject book : books) {
			if (book.getId().equals(bookId)) {
				books.remove(book);
				return;
			}
		}
    }

}
