package sk.umb.example.library.book.controller;

import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.book.service.BookService;
import sk.umb.example.library.book.service.BookDetailDataTransferObject;
import sk.umb.example.library.book.service.BookRequestDataTransferObject;

import java.util.List;

@RestController
public class BookController {

	private final BookService bookService;
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/api/books")
	public List<BookDetailDataTransferObject> searchBook(@RequestParam(required = false) String bookName) {
		System.out.println("Search books was called, " + bookName);
		return bookService.getBooks();
	}

	@GetMapping("/api/books/{bookId}")
	public BookDetailDataTransferObject getBookById(@PathVariable Long bookId) {
		System.out.println("Get book was called, " + bookId);
		return bookService.getBookById(bookId);
	}

	@PostMapping("/api/books")
	public String createBook(@RequestBody BookRequestDataTransferObject book) {
		System.out.println("Create book was called.");
		return "id: " + bookService.createBook(book);
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

	@PutMapping("/api/books/{bookId}/bookCategory/{bookCategoryId}")
	public void addBookCategory(@PathVariable Long bookId, @PathVariable Long bookCategoryId) {
		System.out.println("Add book category, bookId: " + bookId + ", bookCategoryId: " + bookCategoryId);
		bookService.addBookCategory(bookId, bookCategoryId);
	}

	@DeleteMapping("/api/books/{bookId}/bookCategory/{bookCategoryId}")
	public void removeBookCategory(@PathVariable Long bookId, @PathVariable Long bookCategoryId) {
		System.out.println("Remove book category, bookId: " + bookId + ", bookCategoryId: " + bookCategoryId);
		bookService.removeBookCategory(bookId, bookCategoryId);
	}
}