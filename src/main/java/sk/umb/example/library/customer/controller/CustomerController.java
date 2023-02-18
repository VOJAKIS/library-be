package sk.umb.example.library.customer.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

	@GetMapping("/api/customers")
	public void searchCustomer(@RequestParam(required = false) String lastName) {
		System.out.println("Search customer was called" + ((!lastName.isEmpty() ? ", " + lastName : ".")));
	}

	@GetMapping("/api/customers/{customerId}")
	public void getCustomer(@PathVariable Long customerId) {
		System.out.println("Get customer was called, " + customerId);
	}

	@PostMapping("/api/customers")
	public void createCustomer() {
		System.out.println("Create customer was called.");
	}

	@PutMapping("/api/customers/{customerId}")
	public void updateCustomer(@PathVariable Long customerId) {
		System.out.println("Update customer was called, " + customerId);
	}

	@DeleteMapping("/api/customers/{customerId}")
	public void deleteCustomer(@PathVariable Long customerId) {
		System.out.println("Delte customer was called, " + customerId);
	}

}
