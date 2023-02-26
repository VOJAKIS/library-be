package sk.umb.example.library.borrowing.service;

import java.util.Date;

import sk.umb.example.library.book.service.BookDetailDataTransferObject;
import sk.umb.example.library.customer.service.CustomerDetailDataTransferObject;

public class BorrowingDetailDataTransferObject {
	private Long id;
	private CustomerDetailDataTransferObject customer;
	private BookDetailDataTransferObject book;
	private Date dateOfBorrowing;
	
	public Date getDateOfBorrowing() {
		return dateOfBorrowing;
	}
	public void setDateOfBorrowing(Date dateOfBorrowing) {
		this.dateOfBorrowing = dateOfBorrowing;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CustomerDetailDataTransferObject getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDetailDataTransferObject customer) {
		this.customer = customer;
	}
	public BookDetailDataTransferObject getBook() {
		return book;
	}
	public void setBook(BookDetailDataTransferObject book) {
		this.book = book;
	}

	
}
