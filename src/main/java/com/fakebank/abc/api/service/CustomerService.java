package com.fakebank.abc.api.service;

import com.fakebank.abc.api.dto.CustomerRequest;
import com.fakebank.abc.api.dto.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse getCustomerById(Long customerId);
}
