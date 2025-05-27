package com.fakebank.abc.api.service;

import com.fakebank.abc.api.dto.CustomerRequest;
import com.fakebank.abc.api.dto.CustomerResponse;
import com.fakebank.abc.api.entity.Customer;
import com.fakebank.abc.api.exception.BnplApiException;
import com.fakebank.abc.api.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    private CustomerRepository customerRepository;
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void testCreateCustomer_AgeValid_AssignsCreditLine() throws BnplApiException {
        CustomerRequest request = new CustomerRequest();
        request.setFirstName("Carlos");
        request.setLastName("Gomez");
        request.setSecondLastName("Hernandez");
        request.setDateOfBirth(LocalDate.now().minusYears(24)); // Edad: 24

        Customer saved = new Customer();
        saved.setFirstName("Carlos");
        saved.setCreditLineAmount(3000.0);
        saved.setAvailableCreditLineAmount(3000.0);

        when(customerRepository.save(any(Customer.class))).thenReturn(saved);

        CustomerResponse response = customerService.createCustomer(request);

        assertEquals(saved.getCreditLineAmount(), response.getCreditLineAmount());
        assertEquals(saved.getAvailableCreditLineAmount(), response.getAvailableCreditLineAmount());
    }

    @Test
    void testCreateCustomer_UnderAge_ThrowsException() {
        CustomerRequest request = new CustomerRequest();
        request.setFirstName("Ana");
        request.setLastName("Perez");
        request.setSecondLastName("Ruiz");
        request.setDateOfBirth(LocalDate.now().minusYears(15)); // Edad: 15

        Exception ex = assertThrows(Exception.class, () -> customerService.createCustomer(request));
        assertTrue(ex.getMessage().toLowerCase().contains("eligible"));
    }

    @Test
    void testGetCustomerById_NotFound_ThrowsException() {
        Long customerId = 333L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        Exception ex = assertThrows(Exception.class, () -> customerService.getCustomerById(customerId));
        assertTrue(ex.getMessage().toLowerCase().contains("not found"));
    }
}
