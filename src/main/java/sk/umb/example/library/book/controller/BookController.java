package sk.umb.example.library.book.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

public class BookController {

    @GetMapping("api/books")
    public List searchBook(@RequestParam(required = false) String categoryName) {
        System.out.println("Searching for book: " + categoryName);
        return Collections.emptyList();
    }

    @GetMapping("api/books/{bookID}")
    public void getBook(@PathVariable Long bookID) {
        System.out.println("Retrieving details about book with ID " + bookID);
    }

    @PostMapping("api/books")
    public void createBook() {
        System.out.println("Creating book ...");
    }

    @PutMapping("api/books/{bookID}")
    public void updateBook(@PathVariable Long bookID) {
        System.out.println("Updating book with ID" + bookID);
    }

    @DeleteMapping("api/books/{bookID}")
    public void deleteBook(@PathVariable Long bookID) {
        System.out.println("Deleting book with ID" + bookID);
    }

}
