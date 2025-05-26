package com.fakebank.abc.api.dto;

import com.fakebank.abc.api.util.LoanStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class LoanResponse {
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private Long customerId;

    @Setter
    @Getter
    private LoanStatus status;

    @Setter
    @Getter
    private LocalDateTime createdAt;

    @Setter
    @Getter
    private PaymentPlan paymentPlan;

    public static class PaymentPlan {
        @Setter
        @Getter
        private Double commissionAmount;

        @Setter
        @Getter
        private List<InstallmentResponse> installments;
    }
}