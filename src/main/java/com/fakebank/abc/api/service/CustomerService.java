package com.fakebank.abc.api.service;

import com.fakebank.abc.api.dto.CustomerRequest;
import com.fakebank.abc.api.dto.CustomerResponse;
import com.fakebank.abc.api.exception.BnplApiException;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request) throws BnplApiException;

    CustomerResponse getCustomerById(Long customerId) throws BnplApiException;
}
