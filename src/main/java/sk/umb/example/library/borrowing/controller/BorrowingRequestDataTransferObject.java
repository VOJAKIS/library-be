package sk.umb.example.library.borrowing.controller;

public class BorrowingRequestDataTransferObject {
	private Long bookId;
	private Long customerId;

	public Long getBookId() { return bookId; }
	public void setBookId(Long bookId) { this.bookId = bookId; }
	public Long getCustomerId() { return customerId; }
	public void setCustomerId(Long customerId) { this.customerId = customerId; }
}
