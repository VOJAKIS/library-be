package sk.umb.example.library.borrowing.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import sk.umb.example.library.book.persistence.entity.BookEntity;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;

@Entity(name = "borrowing")
public class BorrowingEntity {
	@Id
	@GeneratedValue
	@Column(name = "id_borrowing", unique = true)
	private Long id;

	@ManyToOne
	private CustomerEntity customer;

	@ManyToOne
	private BookEntity book;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public BookEntity getBook() {
		return book;
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}

	

}
