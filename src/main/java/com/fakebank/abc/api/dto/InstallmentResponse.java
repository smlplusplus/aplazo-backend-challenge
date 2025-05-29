package com.fakebank.abc.api.dto;

import com.fakebank.abc.api.util.InstallmentStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
public class InstallmentResponse {
    @Setter
    @Getter
    private Double amount;

    @Setter
    @Getter
    private LocalDate scheduledPaymentDate;

    @Setter
    @Getter
    private InstallmentStatus status;
}
