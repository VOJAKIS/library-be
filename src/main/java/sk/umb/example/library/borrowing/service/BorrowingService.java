package sk.umb.example.library.borrowing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sk.umb.example.library.book.controller.BookController;
import sk.umb.example.library.borrowing.controller.BorrowingRequestDataTransferObject;
import sk.umb.example.library.customer.controller.CustomerController;

@Service
public class BorrowingService {
	
	private final List<BorrowingDataTransferObject> borrowings = new ArrayList<>();
	private Long lastIndex = 0L;

	public List<BorrowingDataTransferObject> getAllBorrowings() {
		return borrowings;
	}

	public List<BorrowingDataTransferObject> serachBorrowingsByBookName(String bookName) {
		List<BorrowingDataTransferObject> searchResult = new ArrayList<>();
		for (BorrowingDataTransferObject borrowing : borrowings) {
			if (borrowing.getBook().getName().toLowerCase().contains(bookName.toLowerCase())) {
				searchResult.add(borrowing);
			}
		}
		return searchResult;
	}

	public BorrowingDataTransferObject getBorrowingById(Long borrowingId) {
		if (borrowingId < 0) { return new BorrowingDataTransferObject(); }
		if (borrowingId >= lastIndex) { return new BorrowingDataTransferObject(); }
		for (BorrowingDataTransferObject borrowing : borrowings) {
			if (borrowing.getId().equals(borrowingId)) {
				return borrowing;
			}
		}
		return new BorrowingDataTransferObject();
	}

	public Long createBorrowing(BorrowingRequestDataTransferObject borrowing) {
		BorrowingDataTransferObject borrowingDataTransferObject = mapToBorrowingDataTransferObject(borrowing);
		if (borrowingDataTransferObject == null) { return null; }
		borrowingDataTransferObject.setId(lastIndex);

		increaseIndexByOne();
		printLastIndex();

		borrowings.add(borrowingDataTransferObject);

		return borrowingDataTransferObject.getId();
	}

	private void increaseIndexByOne() {
		lastIndex++;
	}
	private void printLastIndex() {
		System.out.println("Last index: " + lastIndex);
	}

	private BorrowingDataTransferObject mapToBorrowingDataTransferObject(BorrowingRequestDataTransferObject borrowing) {
		BorrowingDataTransferObject borrowingDataTransferObject = new BorrowingDataTransferObject();
		
		// >FeelsGood moment
		Long customerId = borrowing.getCustomerId();
		if (customerId == null) { return null; }
		borrowingDataTransferObject.setCustomer(CustomerController.customerServiceGlobal.getCustomerById(customerId));
		
		Long bookId = borrowing.getBookId();
		if (bookId == null) { return null; }
		borrowingDataTransferObject.setBook(BookController.bookServiceGlobal.getBookById(bookId));

		return borrowingDataTransferObject;
	}

	public void updateBorrowing(Long borrowingId, BorrowingRequestDataTransferObject borrowing) {
		for (BorrowingDataTransferObject borrowingFromList : borrowings) {
			if (borrowingFromList.getId().equals(borrowingId)) {

				Long customerId = borrowing.getCustomerId();
				if (customerId == null) { return; }
				borrowingFromList.setCustomer(CustomerController.customerServiceGlobal.getCustomerById(customerId));
				
				Long bookId = borrowing.getBookId();
				if (bookId == null) { return; }
				borrowingFromList.setBook(BookController.bookServiceGlobal.getBookById(bookId));

				return;
			}
		}
	}

	public void deleteBorrowing(Long borrowingId) {
		if (borrowingId < 0) { return; }
		if (borrowingId >= lastIndex) { return; }

		for (BorrowingDataTransferObject borrowingFromList : borrowings) {
			if (borrowingFromList.getId().equals(borrowingId)) {
				borrowings.remove(borrowingFromList);
				return;
			}
		}
	}

}
