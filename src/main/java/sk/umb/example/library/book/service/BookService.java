package sk.umb.example.library.book.service;

import org.springframework.stereotype.Service;
import sk.umb.example.library.book.controller.BookRequestDataTransferObject;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final List<BookDataTransferObject> books = new ArrayList<>();
    private Long lastIndex = 0L;

    public List<BookDataTransferObject> getBooks() {
        return books;
    }

    public BookDataTransferObject getBookById(Long bookId) {
        if (bookId < 0) { return null; }
        if (bookId >= lastIndex) { return null; }

        for (BookDataTransferObject book : books) {
			if (book.getId().equals(bookId)) {
				return book;
			}
		}
        return new BookDataTransferObject();
    }

    public Long createBook(BookRequestDataTransferObject book) {
        BookDataTransferObject bookDataTransferObject = mapToBookDataTransferObject(book);
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

    private BookDataTransferObject mapToBookDataTransferObject(BookRequestDataTransferObject book) {
        BookDataTransferObject bookDataTransferObject = new BookDataTransferObject();

        bookDataTransferObject.setAuthorFirstName(book.getAuthorFirstName());
        bookDataTransferObject.setAuthorLastName(book.getAuthorLastName());
        bookDataTransferObject.setTitle(book.getTitle());
        bookDataTransferObject.setIsbn(book.getIsbn());
        bookDataTransferObject.setBookCount(book.getBookCount());
        bookDataTransferObject.setCategoryIds(book.getCategoryIds());

        return bookDataTransferObject;
    }

    public void updateBook(Long bookId, BookRequestDataTransferObject book) {
        for(BookDataTransferObject bookDataTransferObject : books) {
            if(bookDataTransferObject.getId().equals(bookId)) {
                bookDataTransferObject = mapToBookDataTransferObject(book);
                return;
            }
        }
    }

    public void deleteBook(Long bookId) {
        if (bookId < 0) { return; }
		if (bookId >= lastIndex) { return; }

        for (BookDataTransferObject book : books) {
			if (book.getId().equals(bookId)) {
				books.remove(book);
				return;
			}
		}
    }

}
