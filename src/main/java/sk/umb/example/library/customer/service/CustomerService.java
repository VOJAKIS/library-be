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

	public List<CustomerDataTransferObject> getCustomers() {
		return customers;
	}

	public CustomerDataTransferObject getCustomerById(Long customerId) {
		int index = customerId.intValue();

		if (index >= customers.size()) {
			// Mali by sme vrátiť error, ako 404, pretože sa customer nenašiel
			return new CustomerDataTransferObject();
			// return null;
		}

		return customers.get(0);
	}

	public Long createCustomer(CustomerRequestDataTransferObject customer) {
		Long customerId = (long)customers.size();

		CustomerDataTransferObject customerDataTransferObject = mapToCustomerDto(customer);
		customerDataTransferObject.setId(customerId);

		customers.add(customerDataTransferObject);

		return customerDataTransferObject.getId();
	}

	private static CustomerDataTransferObject mapToCustomerDto(CustomerRequestDataTransferObject customer) {
		CustomerDataTransferObject customerDataTransferObject = new CustomerDataTransferObject();

		customerDataTransferObject.setFirstname(customer.getFirstName());
		customerDataTransferObject.setLastname(customer.getLastName());
		customerDataTransferObject.setContact(customer.getContact());

		return customerDataTransferObject;
	}

	public void updateCustomer(Long customerId, CustomerRequestDataTransferObject customer) {
		// TODO: dokončiť
	}

	public void deleteCustomer(Long customerId) {
		// TODO: dokončiť
	}

}
