package sk.umb.example.library.customer.service;

public class CustomerDataTransferObject {
	private Long id;
	private String firstName;
	private String lastName;
	private String contactEmail;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
}