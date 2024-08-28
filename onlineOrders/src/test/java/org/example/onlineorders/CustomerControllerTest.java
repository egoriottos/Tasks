package org.example.onlineorders;

import org.example.onlineorders.controller.CustomerController;
import org.example.onlineorders.entity.Customer;
import org.example.onlineorders.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerControllerTest {
    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;
    private Customer customer;

    @Test
    public void testCreateCustomer(){
        MockitoAnnotations.initMocks(this);
        customer = Customer.builder()
                .id(1L)
                .phone("+79531728296")
                .email("egor@mail.ru")
                .firstname("john")
                .lastname("doe")
                .build();
        when(customerService.create(customer)).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.createCustomer(customer);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(customer, response.getBody());
    }
}
