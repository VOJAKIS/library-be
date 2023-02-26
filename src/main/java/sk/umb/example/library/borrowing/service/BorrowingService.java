package sk.umb.example.library.borrowing.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import sk.umb.example.library.book.controller.BookController;
import sk.umb.example.library.customer.controller.CustomerController;

@Service
public class BorrowingService {
	
	private final List<BorrowingDetailDataTransferObject> borrowings = new ArrayList<>();
	private Long lastIndex = 0L;

	public List<BorrowingDetailDataTransferObject> getAllBorrowings() {
		return borrowings;
	}

	public List<BorrowingDetailDataTransferObject> serachBorrowingsByBookName(String bookName) {
		List<BorrowingDetailDataTransferObject> searchResult = new ArrayList<>();
		for (BorrowingDetailDataTransferObject borrowing : borrowings) {
			if (borrowing.getBook().getTitle().toLowerCase().contains(bookName.toLowerCase())) {
				searchResult.add(borrowing);
			}
		}
		return searchResult;
	}

	public BorrowingDetailDataTransferObject getBorrowingById(Long borrowingId) {
		if (borrowingId < 0) { return new BorrowingDetailDataTransferObject(); }
		if (borrowingId >= lastIndex) { return new BorrowingDetailDataTransferObject(); }
		for (BorrowingDetailDataTransferObject borrowing : borrowings) {
			if (borrowing.getId().equals(borrowingId)) {
				return borrowing;
			}
		}
		return new BorrowingDetailDataTransferObject();
	}

	public Long createBorrowing(BorrowingRequestDataTransferObject borrowing) {
		BorrowingDetailDataTransferObject borrowingDataTransferObject = mapToBorrowingDataTransferObject(borrowing);
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

	private BorrowingDetailDataTransferObject mapToBorrowingDataTransferObject(BorrowingRequestDataTransferObject borrowing) {
		BorrowingDetailDataTransferObject borrowingDataTransferObject = new BorrowingDetailDataTransferObject();
		
		// >FeelsGood moment
		Long customerId = borrowing.getCustomerId();
		if (customerId == null) { return null; }
		Long bookId = borrowing.getBookId();
		if (bookId == null) { return null; }
		borrowingDataTransferObject.setCustomer(CustomerController.customerServiceGlobal.getCustomerById(customerId));
		borrowingDataTransferObject.setBook(BookController.bookServiceGlobal.getBookById(bookId));
		borrowingDataTransferObject.setDateOfBorrowing(new Date());

		return borrowingDataTransferObject;
	}

	public void updateBorrowing(Long borrowingId, BorrowingRequestDataTransferObject borrowing) {
		if (borrowingId < 0) { return; }
		if (borrowingId >= lastIndex) { return; }
		
		for (BorrowingDetailDataTransferObject borrowingDataTransferObject : borrowings) {
			if (borrowingDataTransferObject.getId().equals(borrowingId)) {
				Long customerId = borrowing.getCustomerId();
				if (customerId == null) { return; }
				Long bookId = borrowing.getBookId();
				if (bookId == null) { return; }
				borrowingDataTransferObject.setCustomer(CustomerController.customerServiceGlobal.getCustomerById(customerId));
				borrowingDataTransferObject.setBook(BookController.bookServiceGlobal.getBookById(bookId));
				borrowingDataTransferObject.setDateOfBorrowing(new Date());
				return;
			}
		}
	}

	public void deleteBorrowing(Long borrowingId) {
		if (borrowingId < 0) { return; }
		if (borrowingId >= lastIndex) { return; }

		for (BorrowingDetailDataTransferObject borrowingFromList : borrowings) {
			if (borrowingFromList.getId().equals(borrowingId)) {
				borrowings.remove(borrowingFromList);
				return;
			}
		}
	}

}
