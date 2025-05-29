package com.fakebank.abc.api.service;

import com.fakebank.abc.api.dto.CustomerRequest;
import com.fakebank.abc.api.dto.CustomerResponse;
import com.fakebank.abc.api.entity.Customer;
import com.fakebank.abc.api.exception.BnplApiException;
import com.fakebank.abc.api.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) throws BnplApiException {
        logger.info("Creating customer {}", request);
        // Validar edad
        LocalDate now = LocalDate.now();
        int age = now.getYear() - request.getDateOfBirth().getYear();
        if (age < 18 || age > 65) throw new BnplApiException("Customer not eligible by age");

        double creditLine = (age <= 25) ? 3000 : (age <= 30) ? 5000 : 8000;

        Customer entity = new Customer();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setSecondLastName(request.getSecondLastName());
        entity.setDateOfBirth(request.getDateOfBirth());
        entity.setCreditLineAmount(creditLine);
        entity.setAvailableCreditLineAmount(creditLine);
        entity.setCreatedAt(LocalDateTime.now());

        Customer saved = customerRepository.save(entity);

        CustomerResponse response = new CustomerResponse();
        response.setId(saved.getId());
        response.setCreditLineAmount(saved.getCreditLineAmount());
        response.setAvailableCreditLineAmount(saved.getAvailableCreditLineAmount());
        response.setCreatedAt(saved.getCreatedAt());
        logger.info("Created customer {}", response);
        return response;
    }

    @Override
    public CustomerResponse getCustomerById(Long customerId) throws BnplApiException {
        logger.info("Getting customer by id {}", customerId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty())
            throw new BnplApiException("Customer not found");
        Customer c = customer.get();
        CustomerResponse response = new CustomerResponse();
        response.setId(c.getId());
        response.setCreditLineAmount(c.getCreditLineAmount());
        response.setAvailableCreditLineAmount(c.getAvailableCreditLineAmount());
        response.setCreatedAt(c.getCreatedAt());
        logger.info("Got customer {}", response);
        return response;
    }
}
