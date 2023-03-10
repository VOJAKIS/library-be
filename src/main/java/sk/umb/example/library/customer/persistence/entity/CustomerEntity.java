package sk.umb.example.library.customer.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;
// import sk.umb.example.library.address.persistence.entity.AddressEntity;

@Entity(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue
	@Column(name = "id_customer")
    private Long id;

	@Column(name = "first_name")
    private String firstName;
		
	@Column(name = "last_name")
    private String lastName;

	@Column(name = "contact_email")
    private String contactEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String emailContact) {
        this.contactEmail = emailContact;
    }
}