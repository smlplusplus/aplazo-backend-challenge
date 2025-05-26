package com.fakebank.abc.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {
    @Setter
    @Getter
    private Long purchaseId;
}
