package sk.umb.example.library.borrowing.persistence.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	@JoinColumn(name = "id_customer", nullable = false)
	private CustomerEntity customer;

	@ManyToOne
	@JoinColumn(name = "id_book", nullable = false)
	private BookEntity book;

	@Column(name = "date_of_borrowing")
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
