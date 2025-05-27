package com.fakebank.abc.api.service;

import com.fakebank.abc.api.dto.LoanRequest;
import com.fakebank.abc.api.dto.LoanResponse;
import com.fakebank.abc.api.entity.Customer;
import com.fakebank.abc.api.entity.Loan;
import com.fakebank.abc.api.exception.BnplApiException;
import com.fakebank.abc.api.repository.CustomerRepository;
import com.fakebank.abc.api.repository.LoanRepository;
import com.fakebank.abc.api.util.LoanStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanServiceImplTest {

    private LoanRepository loanRepository;
    private CustomerRepository customerRepository;
    private LoanServiceImpl loanService;

    @BeforeEach
    void setUp() {
        loanRepository = mock(LoanRepository.class);
        customerRepository = mock(CustomerRepository.class);
        loanService = new LoanServiceImpl(loanRepository, customerRepository);
    }

    @Test
    void testCreateLoan_EnoughCredit_Success() throws BnplApiException {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("Carlos");
        customer.setCreditLineAmount(5000.0);
        customer.setAvailableCreditLineAmount(5000.0);

        LoanRequest request = new LoanRequest();
        request.setCustomerId(customerId);
        request.setAmount(2000.0);

        Loan savedLoan = new Loan();
        savedLoan.setCustomer(customer);
        savedLoan.setAmount(2000.0);
        savedLoan.setStatus(LoanStatus.ACTIVE);
        savedLoan.setCreatedAt(LocalDateTime.now());

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(loanRepository.save(any(Loan.class))).thenReturn(savedLoan);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        LoanResponse response = loanService.createLoan(request);

        assertEquals(savedLoan.getAmount(), request.getAmount());
        assertEquals(customerId, response.getCustomerId());
        assertEquals(LoanStatus.ACTIVE, response.getStatus());
    }

    @Test
    void testCreateLoan_InsufficientCredit_ThrowsException() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("Carlos");
        customer.setAvailableCreditLineAmount(1000.0);

        LoanRequest request = new LoanRequest();
        request.setCustomerId(customerId);
        request.setAmount(3000.0);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Exception ex = assertThrows(Exception.class, () -> loanService.createLoan(request));
        assertTrue(ex.getMessage().toLowerCase().contains("credit"));
    }

    @Test
    void testCreateLoan_CustomerNotFound_ThrowsException() {
        Long customerId = 333L;
        LoanRequest request = new LoanRequest();
        request.setCustomerId(customerId);
        request.setAmount(1500.0);

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        Exception ex = assertThrows(Exception.class, () -> loanService.createLoan(request));
        assertTrue(ex.getMessage().toLowerCase().contains("customer not found"));
    }
}
