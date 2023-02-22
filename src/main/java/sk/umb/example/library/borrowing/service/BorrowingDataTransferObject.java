package sk.umb.example.library.borrowing.service;

import sk.umb.example.library.book.service.BookDataTransferObject;
import sk.umb.example.library.customer.service.CustomerDataTransferObject;

public class BorrowingDataTransferObject {
	private Long id;
	private CustomerDataTransferObject customer;
	private BookDataTransferObject book;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CustomerDataTransferObject getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDataTransferObject customer) {
		this.customer = customer;
	}
	public BookDataTransferObject getBook() {
		return book;
	}
	public void setBook(BookDataTransferObject book) {
		this.book = book;
	}

	
}
