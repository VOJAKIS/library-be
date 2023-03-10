package sk.umb.example.library.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;
import sk.umb.example.library.customer.persistence.repository.CustomerRepository;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	// Pri vymazaní customera cez customers.remove(customerId) sa nám pomenia indexy
	// Je možné použiť Mapu
	private final List<CustomerDetailDataTransferObject> customers = new ArrayList<>();
	private Long lastIndex = (long)customers.size();

	public List<CustomerDetailDataTransferObject> getAllCustomers() {
		// return customers;
		return mapToDataTransferObjectList(customerRepository.findAll());
	}

	public List<CustomerDetailDataTransferObject> getCustomerByLastName(String lastName) {
		return mapToDataTransferObjectList(customerRepository.findByLastName(lastName));
	}

	public CustomerDetailDataTransferObject getCustomerById(Long customerId) {
		return mapToDataTransferObject(getCustomerEntityById(customerId));
	}

	private void increaseIndexByOne() {
		lastIndex++;
	}
	private void printLastIndex() {
		System.out.println("Last index: " + lastIndex);
	}

	@Transactional
	public Long createCustomer(CustomerRequestDataTransferObject customerRequestDataTransferObject) {
		CustomerEntity customerEntity = mapToEntity(customerRequestDataTransferObject);

		return customerRepository.save(customerEntity).getId();
	}

	private CustomerEntity mapToEntity(CustomerRequestDataTransferObject customerRequestDataTransferObject) {
		CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setFirstName(customerRequestDataTransferObject.getFirstName());
        customerEntity.setLastName(customerRequestDataTransferObject.getLastName());
        customerEntity.setContactEmail(customerRequestDataTransferObject.getContactEmail());

        return customerEntity;
	}

	private CustomerEntity getCustomerEntityById(Long customerId) {
		Optional<CustomerEntity> customer = customerRepository.findById(customerId);

        if (customer.isEmpty()) {
            throw new IllegalArgumentException("Customer not found. ID: " + customerId);
        }

        return customer.get();
	}

	private List<CustomerDetailDataTransferObject> mapToDataTransferObjectList(Iterable<CustomerEntity> customerEntities) {
		List<CustomerDetailDataTransferObject> customers = new ArrayList<>();
	
		customerEntities.forEach(customerEntity -> {
			CustomerDetailDataTransferObject dto = mapToDataTransferObject(customerEntity);
			customers.add(dto);
		});
	
		return customers;
	}

	private CustomerDetailDataTransferObject mapToDataTransferObject(CustomerEntity customerEntity) {
		CustomerDetailDataTransferObject dto = new CustomerDetailDataTransferObject();
		
        dto.setId(customerEntity.getId());
        dto.setFirstName(customerEntity.getFirstName());
        dto.setLastName(customerEntity.getLastName());
        dto.setContactEmail(customerEntity.getContactEmail());

        return dto;
	}

	private static CustomerDetailDataTransferObject mapToCustomerDataTransferObject(CustomerRequestDataTransferObject customer) {
		CustomerDetailDataTransferObject customerDataTransferObject = new CustomerDetailDataTransferObject();

		customerDataTransferObject.setFirstName(customer.getFirstName());
		customerDataTransferObject.setLastName(customer.getLastName());
		customerDataTransferObject.setContactEmail(customer.getContactEmail());

		return customerDataTransferObject;
	}

	// TODO: Skončil si tu.
	// TODO: Prerob vecičky z githubu
	@Transactional
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

	@Transactional
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
