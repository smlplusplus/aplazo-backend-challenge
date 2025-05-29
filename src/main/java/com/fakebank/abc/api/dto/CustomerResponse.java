package com.fakebank.abc.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class CustomerResponse {
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private LocalDateTime createdAt;

    @Setter
    @Getter
    private double creditLineAmount;

    @Setter
    @Getter
    private double availableCreditLineAmount;
}
