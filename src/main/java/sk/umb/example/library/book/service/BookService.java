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
        if (index >= books.size())
            return new BookDataTransferObject();
        return books.get(index);
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
        for(BookDataTransferObject bookDTO : books) {
            if(bookDTO.getId().equals(bookID)) {
                bookDTO.setId(bookID);
                bookDTO.setIsbn(book.getIsbn());
                bookDTO.setAuthorFirstName(book.getAuthorFirstName());
                bookDTO.setAuthorLastName(book.getAuthorLastName());
                bookDTO.setName(book.getName());
                bookDTO.setBookCount(book.getBookCount());
                return;
            }
        }
    }

    public void deleteBook(Long bookID) {
        //TODO: Refactor later on
        int indexFound = 0;
        for(BookDataTransferObject bookDTO : books) {
            if(bookDTO.getId().equals(bookID)) {
                indexFound = books.indexOf(bookDTO);
                books.remove(indexFound);
                break;
            }
        }
        for(int i = indexFound; i < books.size(); i++)
            books.get(i).setId((long) i);
    }

}
