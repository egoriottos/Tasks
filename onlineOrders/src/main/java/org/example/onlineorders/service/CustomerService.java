package org.example.onlineorders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.onlineorders.entity.Customer;
import org.example.onlineorders.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    public Customer create(String customer) {
        Customer customer1 = null;
        try {
            customer1 = objectMapper.readValue(customer, Customer.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Customer newCustomer = Customer.builder()
                .phone(customer1.getPhone())
                .email(customer1.getEmail())
                .firstname(customer1.getFirstname())
                .lastname(customer1.getLastname())
                .build();
        customerRepository.save(newCustomer);
        return newCustomer;
    }
}
