package sk.umb.example.library.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sk.umb.example.library.customer.controller.CustomerRequestDataTransferObject;

@Service
public class CustomerService {

	// Pri vymazaní customera cez customers.remove(customerId) sa nám pomenia indexy
	// Je možné použiť Mapu
	private final List<CustomerDataTransferObject> customers = new ArrayList<>();
	private Long lastIndex = (long)customers.size();

	public List<CustomerDataTransferObject> getCustomers() {
		return customers;
	}

	public List<CustomerDataTransferObject> getCustomers(String lastName) {
		List<CustomerDataTransferObject> searchResult = new ArrayList<>();
		for (CustomerDataTransferObject customerFromList : customers) {
			if (customerFromList.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
				searchResult.add(customerFromList);
			}
		}
		return searchResult;
	}

	public CustomerDataTransferObject getCustomerById(Long customerId) {
		if (customerId < 0) { return new CustomerDataTransferObject(); }

		// Mali by sme vrátiť error, ako 404, pretože sa customer nenašiel.
		// return null;
		if (customerId >= lastIndex) { return new CustomerDataTransferObject(); }

		for (CustomerDataTransferObject customer : customers) {
			if (customer.getId().equals(customerId)) {
				return customer;
			}
		}
		
		return new CustomerDataTransferObject();
	}

	private void increaseIndexByOne() {
		lastIndex++;
	}
	private void printLastIndex() {
		System.out.println("Last index: " + lastIndex);
	}

	public Long createCustomer(CustomerRequestDataTransferObject customer) {
		CustomerDataTransferObject customerDataTransferObject = mapToCustomerDataTransferObject(customer);
		customerDataTransferObject.setId(lastIndex);

		increaseIndexByOne();
		printLastIndex();
		
		customers.add(customerDataTransferObject);

		return customerDataTransferObject.getId();
	}

	private static CustomerDataTransferObject mapToCustomerDataTransferObject(CustomerRequestDataTransferObject customer) {
		CustomerDataTransferObject customerDataTransferObject = new CustomerDataTransferObject();

		customerDataTransferObject.setFirstName(customer.getFirstName());
		customerDataTransferObject.setLastName(customer.getLastName());
		customerDataTransferObject.setContactEmail(customer.getContactEmail());

		return customerDataTransferObject;
	}

	public void updateCustomer(Long customerId, CustomerRequestDataTransferObject customer) {
		for (CustomerDataTransferObject customerFromList : customers) {
			if (customerFromList.getId().equals(customerId)) {
				customerFromList = mapToCustomerDataTransferObject(customer);
				return;
			}
		}
	}

	public void deleteCustomer(Long customerId) {
		if (customerId < 0) { return; }
		if (customerId >= lastIndex) { return; }

		for (CustomerDataTransferObject customerFromList : customers) {
			if (customerFromList.getId().equals(customerId)) {
				customers.remove(customerFromList);
				return;
			}
		}
	}

}
