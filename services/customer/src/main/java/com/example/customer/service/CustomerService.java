package com.example.customer.service;

import com.example.customer.exception.CustomerNotFoundException;
import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import io.micrometer.common.util.StringUtils;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public String createCustomer(Customer request) {
        System.out.println(request);
        var customer = this.repository.save(request);
        return customer.getId();
    }

    public void updateCustomer(Customer request) {
        var customer = this.repository.findById(request.getId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID: %s", request.getId())
                ));
        mergeCustomer(customer, request);
        this.repository.save(customer);
    }

    private void mergeCustomer(Customer customer, Customer request) {
        if (StringUtils.isNotBlank(request.getFirstname())) {
            customer.setFirstname(request.getFirstname());
        }
        if (StringUtils.isNotBlank(request.getEmail())) {
            customer.setEmail(request.getEmail());
        }
        if (request.getAddress()!= null) {
            customer.setAddress(request.getAddress());
        }
    }

    public List<Customer> findAllCustomers() {
        return  this.repository.findAll();

    }

    public Customer findById(String id) {
        return this.repository.findById(id)

                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id)));
    }


    public void deleteCustomer(String id) {
        this.repository.deleteById(id);
    }
}
