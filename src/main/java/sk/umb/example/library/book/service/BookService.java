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
        // if (bookId < 0) { return new BookDataTransferObject(); }
        // if (bookId >= lastIndex) { return new BookDataTransferObject(); }

        for (BookDataTransferObject book : books) {
			if (book.getId().equals(bookId)) {
				return book;
			}
		}
        // return new BookDataTransferObject();
        return null;
    }

    public Long createBook(BookRequestDataTransferObject book) {
        // Long bookId = (long) books.size();
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
        BookDataTransferObject bookDTO = new BookDataTransferObject();

        bookDTO.setName(book.getName());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setAuthorFirstName(book.getAuthorFirstName());
        bookDTO.setAuthorLastName(book.getAuthorLastName());
        bookDTO.setBookCount(book.getBookCount());

        return bookDTO;
    }


    public void updateBook(Long bookId, BookRequestDataTransferObject book) {
        for(BookDataTransferObject bookDTO : books) {
            if(bookDTO.getId().equals(bookId)) {
                bookDTO.setIsbn(book.getIsbn());
                bookDTO.setAuthorFirstName(book.getAuthorFirstName());
                bookDTO.setAuthorLastName(book.getAuthorLastName());
                bookDTO.setName(book.getName());
                bookDTO.setBookCount(book.getBookCount());
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
