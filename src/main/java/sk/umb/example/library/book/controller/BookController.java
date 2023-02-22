package sk.umb.example.library.book.controller;

import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.book.service.BookService;
import sk.umb.example.library.book.service.BookDataTransferObject;

import java.util.List;

@RestController
public class BookController {

	private final BookService bookService;
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/api/books")
	public List<BookDataTransferObject> searchBook(@RequestParam(required = false) String bookName) {
		//System.out.println("Search books was called" + ((!bookName.isEmpty() ? ", " + bookName : ".")));
		return bookService.getBooks();
	}

	@GetMapping("/api/books/{bookId}")
	public BookDataTransferObject getBook(@PathVariable Long bookId) {
		System.out.println("Get book was called, " + bookId);
		return bookService.getBookByID(bookId);
	}

	@PostMapping("/api/books")
	public String createBook(@RequestBody BookRequestDataTransferObject book) {
		System.out.println("Create book was called.");
		return "id:" + bookService.createBook(book);
	}

	@PutMapping("/api/books/{bookId}")
	public void updateBook(@PathVariable Long bookId, @RequestBody BookRequestDataTransferObject book) {
		System.out.println("Update book was called, " + bookId);
		bookService.updateBook(bookId, book);
	}

	@DeleteMapping("/api/books/{bookId}")
	public void deleteCustomer(@PathVariable Long bookId) {
		System.out.println("Delete book was called, " + bookId);
		bookService.deleteBook(bookId);
	}
}