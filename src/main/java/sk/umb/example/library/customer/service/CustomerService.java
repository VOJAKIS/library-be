package sk.umb.example.library.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	// Pri vymazaní customera cez customers.remove(customerId) sa nám pomenia indexy
	// Je možné použiť Mapu
	private final List<CustomerDetailDataTransferObject> customers = new ArrayList<>();
	private Long lastIndex = (long)customers.size();

	public List<CustomerDetailDataTransferObject> getCustomers() {
		return customers;
	}

	public List<CustomerDetailDataTransferObject> getCustomers(String lastName) {
		List<CustomerDetailDataTransferObject> searchResult = new ArrayList<>();
		for (CustomerDetailDataTransferObject customerFromList : customers) {
			if (customerFromList.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
				searchResult.add(customerFromList);
			}
		}
		return searchResult;
	}

	public CustomerDetailDataTransferObject getCustomerById(Long customerId) {
		if (customerId < 0) { return new CustomerDetailDataTransferObject(); }

		// Mali by sme vrátiť error, ako 404, pretože sa customer nenašiel.
		// return null;
		if (customerId >= lastIndex) { return new CustomerDetailDataTransferObject(); }

		for (CustomerDetailDataTransferObject customer : customers) {
			if (customer.getId().equals(customerId)) {
				return customer;
			}
		}
		
		return new CustomerDetailDataTransferObject();
	}

	private void increaseIndexByOne() {
		lastIndex++;
	}
	private void printLastIndex() {
		System.out.println("Last index: " + lastIndex);
	}

	public Long createCustomer(CustomerRequestDataTransferObject customer) {
		CustomerDetailDataTransferObject customerDataTransferObject = mapToCustomerDataTransferObject(customer);
		customerDataTransferObject.setId(lastIndex);

		increaseIndexByOne();
		printLastIndex();
		
		customers.add(customerDataTransferObject);

		return customerDataTransferObject.getId();
	}

	private static CustomerDetailDataTransferObject mapToCustomerDataTransferObject(CustomerRequestDataTransferObject customer) {
		CustomerDetailDataTransferObject customerDataTransferObject = new CustomerDetailDataTransferObject();

		customerDataTransferObject.setFirstName(customer.getFirstName());
		customerDataTransferObject.setLastName(customer.getLastName());
		customerDataTransferObject.setContactEmail(customer.getContactEmail());

		return customerDataTransferObject;
	}

	public void updateCustomer(Long customerId, CustomerRequestDataTransferObject customer) {
		if (customerId < 0) { return; }
		if (customerId >= lastIndex) { return; }
		
		for (CustomerDetailDataTransferObject customerDataTransferObject : customers) {
			System.out.println(customerDataTransferObject.getId() + " : " + customerId);
			if (customerDataTransferObject.getId().equals(customerId)) {
				// Cez map to nefunguje
				customerDataTransferObject.setFirstName(customer.getFirstName());
				customerDataTransferObject.setLastName(customer.getLastName());
				customerDataTransferObject.setContactEmail(customer.getContactEmail());
				return;
			}
		}
	}

	public void deleteCustomer(Long customerId) {
		if (customerId < 0) { return; }
		if (customerId >= lastIndex) { return; }

		for (CustomerDetailDataTransferObject customerFromList : customers) {
			if (customerFromList.getId().equals(customerId)) {
				customers.remove(customerFromList);
				return;
			}
		}
	}

}
