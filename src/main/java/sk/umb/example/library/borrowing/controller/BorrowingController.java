package sk.umb.example.library.borrowing.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BorrowingController {
	
	@GetMapping("/api/borrowings")
	public void searchBorrowing(@RequestParam(required = false) String lastName) {
		System.out.println("Search borrowing was called" + ((!lastName.isEmpty() ? ", " + lastName : ".")));
	}

	@GetMapping("/api/borrowings/{borrowingId}")
	public void getBorrowing(@PathVariable Long borrowingId) {
		System.out.println("Get borrowing was called, " + borrowingId);
	}

	@PostMapping("/api/borrowings")
	public void createBorrowing() {
		System.out.println("Create borrowing was called.");
	}

	@PutMapping("/api/borrowings/{borrowingId}")
	public void updateBorrowing(@PathVariable Long borrowingId) {
		System.out.println("Update borrowing was called, " + borrowingId);
	}

	@DeleteMapping("/api/borrowings/{borrowingId}")
	public void deleteBorrowing(@PathVariable Long borrowingId) {
		System.out.println("Delte borrowing was called, " + borrowingId);
	}

}
