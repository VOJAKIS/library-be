package sk.umb.example.library.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
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

	public List<CustomerDetailDataTransferObject> getAllCustomers() {
		// return customers;
		return mapToDataTransferObjectList(customerRepository.findAll());
	}

	public List<CustomerDetailDataTransferObject> getCustomersByLastName(String lastName) {
		return mapToDataTransferObjectList(customerRepository.findByLastName(lastName));
	}

	public CustomerDetailDataTransferObject getCustomerById(Long customerId) {
		return mapToDataTransferObject(getCustomerEntityById(customerId));
	}

	@Transactional
	public Long createCustomer(CustomerRequestDataTransferObject customerRequestDataTransferObject) {
		CustomerEntity customerEntity = mapToEntity(customerRequestDataTransferObject);

		return customerRepository.save(customerEntity).getId();
	}

	@Transactional
	public void updateCustomer(Long customerId, CustomerRequestDataTransferObject customerRequestDataTransferObject) {
		CustomerEntity customerEntity = getCustomerEntityById(customerId);

        if (! Strings.isEmpty(customerRequestDataTransferObject.getFirstName())) {
            customerEntity.setFirstName(customerRequestDataTransferObject.getFirstName());
        }

        if (! Strings.isEmpty(customerRequestDataTransferObject.getLastName())) {
            customerEntity.setLastName(customerRequestDataTransferObject.getLastName());
        }

        if (! Strings.isEmpty(customerRequestDataTransferObject.getContactEmail())) {
            customerEntity.setContactEmail(customerRequestDataTransferObject.getContactEmail());
        }

        customerRepository.save(customerEntity);
	}

	@Transactional
	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
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


	

}
