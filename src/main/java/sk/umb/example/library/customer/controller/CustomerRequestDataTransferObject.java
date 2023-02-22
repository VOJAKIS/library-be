package sk.umb.example.library.customer.controller;

public class CustomerRequestDataTransferObject {
	private String firstName;
	private String lastName;
	private String contact;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastname) {
		this.lastName = lastname;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}	
}