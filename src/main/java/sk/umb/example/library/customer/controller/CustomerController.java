package sk.umb.example.library.customer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import sk.umb.example.library.customer.service.CustomerDataTransferObject;
import sk.umb.example.library.customer.service.CustomerService;

@RestController
public class CustomerController {

	/* Môže byť aj takto
	@Autowired
	private CustomerService customerService;
	*/

	/* Ako by to malo byť */
	// Najlepšie je mať final
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	// get customer vracia list, zoznam viacerých
	@GetMapping("/api/customers")
	public List<CustomerDataTransferObject> searchCustomer(@RequestParam(required = false) String lastName) {
		System.out.println("Search customer was called, " + lastName);
		return customerService.getCustomers();
	}

	// Pri customer id ccem vrátiť len jedného customera
	@GetMapping("/api/customers/{customerId}")
	public CustomerDataTransferObject getCustomer(@PathVariable Long customerId) {
		System.out.println("Get customer was called, " + customerId);
		return customerService.getCustomerById(customerId);
	}

	// Best practice: vrátiť ID vytvorenej entity
	@PostMapping("/api/customers")
	public String createCustomer(@RequestBody CustomerRequestDataTransferObject customer) {
		System.out.println("Create customer was called.");
		return "id: " + customerService.createCustomer(customer);
	}

	// Môžeme spraviť editáciu podľa seba, buď všetko v POST alebo takto
	@PutMapping("/api/customers/{customerId}")
	public void updateCustomer(@PathVariable Long customerId, @RequestBody CustomerRequestDataTransferObject customer) {
		System.out.println("Update customer was called, " + customerId);
		// TODO: Dokončiť updatovanie, nájdem customera, setnem nové hodnoty
		customerService.updateCustomer(customerId, customer);
	}

	// TODO: Spraviť vymazávanie
	@DeleteMapping("/api/customers/{customerId}")
	public void deleteCustomer(@PathVariable Long customerId) {
		System.out.println("Delte customer was called, " + customerId);
		customerService.deleteCustomer(customerId);
	}

}
