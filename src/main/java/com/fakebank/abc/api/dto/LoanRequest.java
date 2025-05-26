package com.fakebank.abc.api.dto;

import lombok.Getter;
import lombok.Setter;

public class LoanRequest {
    @Setter
    @Getter
    private Long customerId;
    @Setter
    @Getter
    private double amount;
}
