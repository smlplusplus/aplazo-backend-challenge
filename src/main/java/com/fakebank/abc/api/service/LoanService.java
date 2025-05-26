package com.fakebank.abc.api.service;

import com.fakebank.abc.api.dto.LoanRequest;
import com.fakebank.abc.api.dto.LoanResponse;
import com.fakebank.abc.api.exception.BnplApiException;

public interface LoanService {
    LoanResponse createLoan(LoanRequest request) throws BnplApiException;

    LoanResponse getLoanById(Long loanId) throws BnplApiException;
}