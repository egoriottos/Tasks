package org.example.onlineorders.service;

import lombok.RequiredArgsConstructor;
import org.example.onlineorders.entity.Customer;
import org.example.onlineorders.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer create(Customer customer) {
        Customer newCustomer = Customer.builder()
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .build();
        customerRepository.save(newCustomer);
        return newCustomer;
    }
}
