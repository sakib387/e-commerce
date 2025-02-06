package com.example.customer.controller;


import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {


    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping

    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid Customer request
    ) {
        return ResponseEntity.ok(this.service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid Customer request
    ) {
        this.service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(this.service.findAllCustomers());
    }


    @GetMapping("/{customer-id}")
    public ResponseEntity<Customer> findById(
            @PathVariable("customer-id") String customerId
    ) {
        System.out.println("sakib");
        return ResponseEntity.ok(this.service.findById(customerId));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @RequestParam("customerId") String customerId
    ) {
        this.service.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }

}
