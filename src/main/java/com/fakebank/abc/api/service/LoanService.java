package com.fakebank.abc.api.service;

import com.fakebank.abc.api.dto.LoanRequest;
import com.fakebank.abc.api.dto.LoanResponse;

public interface LoanService {
    LoanResponse createLoan(LoanRequest request);

    LoanResponse getLoanById(Long loanId);
}