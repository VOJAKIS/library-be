package sk.umb.example.library.book.service;

import org.springframework.stereotype.Service;
import sk.umb.example.library.book.controller.BookRequestDataTransferObject;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final List<BookDataTransferObject> books = new ArrayList<>();

    public List<BookDataTransferObject> getBooks() {
        return books;
    }

    public BookDataTransferObject getBookByID(Long bookID) {
        int index = bookID.intValue();
        return (index >= books.size()) ? new BookDataTransferObject() : books.get(0);
    }

    public Long createBook(BookRequestDataTransferObject book) {
        Long bookID = (long) books.size();
        BookDataTransferObject bookDTO = mapToBookDTO(book);
        bookDTO.setId(bookID);

        books.add(bookDTO);
        return bookDTO.getId();
    }

    private BookDataTransferObject mapToBookDTO(BookRequestDataTransferObject book) {
        BookDataTransferObject bookDTO = new BookDataTransferObject();
        bookDTO.setName(book.getName());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setAuthorFirstName(book.getAuthorFirstName());
        bookDTO.setAuthorLastName(book.getAuthorLastName());
        bookDTO.setBookCount(book.getBookCount());

        return bookDTO;
    }


    public void updateBook(Long bookID, BookRequestDataTransferObject book) {
        //TODO: Implement the Update Book Action
    }

    public void deleteBook(Long bookID) {
        for(BookDataTransferObject bookDTO : books) {
            if(bookDTO.getId().equals(bookID))
                books.remove(books.indexOf(bookDTO));
        }
    }

}
