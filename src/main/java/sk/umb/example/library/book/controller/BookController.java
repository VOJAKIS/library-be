package sk.umb.example.library.book.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
	@GetMapping("/api/books")
	public void searchBook(@RequestParam(required = false) String bookName) {
		System.out.println("Search books was called" + ((!bookName.isEmpty() ? ", " + bookName : ".")));
		
	}

	@GetMapping("/api/books/{bookId}")
	public void getBook(@PathVariable Long bookId) {
		System.out.println("Get book was called, " + bookId);
	}

	@PostMapping("/api/books")
	public void createBook() {
		System.out.println("Create book was called.");
	}

	@PutMapping("/api/books/{bookId}")
	public void updateBook(@PathVariable Long bookId) {
		System.out.println("Update book was called, " + bookId);
	}

	@DeleteMapping("/api/books/{bookId}")
	public void deleteCustomer(@PathVariable Long bookId) {
		System.out.println("Delte book was called, " + bookId);
	}
}