package sk.umb.example.library.borrowing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import sk.umb.example.library.borrowing.service.BorrowingDetailDataTransferObject;
import sk.umb.example.library.borrowing.service.BorrowingRequestDataTransferObject;
import sk.umb.example.library.borrowing.service.BorrowingService;

@RestController
public class BorrowingController {

	private final BorrowingService borrowingService;
	public BorrowingController(BorrowingService borrowingService) {
		this.borrowingService = borrowingService;
	}
	
	@GetMapping("/api/borrowings")
	public List<BorrowingDetailDataTransferObject> searchBorrowing(@RequestParam(required = false) String bookName) {
		if (bookName == null) {
			System.out.println("Search borrowing was called.");
			return borrowingService.getAllBorrowings();
		} else {
			System.out.println("Search borrowing was called, " + bookName);
			return borrowingService.serachBorrowingsByBookName(bookName);
		}
	}

	@GetMapping("/api/borrowings/{borrowingId}")
	public BorrowingDetailDataTransferObject getBorrowing(@PathVariable Long borrowingId) {
		System.out.println("Get borrowing was called, " + borrowingId);
		return borrowingService.getBorrowingById(borrowingId);
	}

	@PostMapping("/api/borrowings")
	public String createBorrowing(@RequestBody BorrowingRequestDataTransferObject borrowing) {
		System.out.println("Create borrowing was called.");
		return "id: " + borrowingService.createBorrowing(borrowing);
	}

	@PutMapping("/api/borrowings/{borrowingId}")
	public void updateBorrowing(@PathVariable Long borrowingId, @RequestBody BorrowingRequestDataTransferObject borrowing) {
		System.out.println("Update borrowing was called, " + borrowingId);
		borrowingService.updateBorrowing(borrowingId, borrowing);
	}

	@DeleteMapping("/api/borrowings/{borrowingId}")
	public void deleteBorrowing(@PathVariable Long borrowingId) {
		System.out.println("Delte borrowing was called, " + borrowingId);
		borrowingService.deleteBorrowing(borrowingId);
	}

}
