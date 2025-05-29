package com.fakebank.abc.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class LoanRequest {
    @Setter
    @Getter
    private Long customerId;
    @Setter
    @Getter
    private double amount;
}
