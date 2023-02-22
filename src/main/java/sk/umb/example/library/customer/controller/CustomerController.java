package sk.umb.example.library.customer.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class CustomerController {

    // Chceme hladat len podla koncoveho mena
    @GetMapping("/api/customers") // spravne pomenovanie
    public List searchCustomer(@RequestParam(required = false) String lastName) {
        System.out.println("Search customer called.");

        return Collections.emptyList();
    }

    @GetMapping("/api/customers/{customerId}") // spravne pomenovanie
    public void getCustomer(@PathVariable Long customerId) {
        System.out.println("Get customer called.");
    }

    @PostMapping("api/customers")
    public void createCustomer() {
        System.out.println("Create customer called:");
    }

    @PutMapping("/api/customers/{customerId}") // spravne pomenovanie
    public void updateCustomer(@PathVariable Long customerId) {
        System.out.println("Update customer called: ID" + customerId);
    }

    @DeleteMapping("api/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        System.out.println("Delete customer called: ID " + customerId);
    }

}

