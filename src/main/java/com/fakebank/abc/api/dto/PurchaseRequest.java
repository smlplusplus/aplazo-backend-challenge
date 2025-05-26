package com.fakebank.abc.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
    @Setter
    @Getter
    private Long clientId;
    @Setter
    @Getter
    private double amount;
}
