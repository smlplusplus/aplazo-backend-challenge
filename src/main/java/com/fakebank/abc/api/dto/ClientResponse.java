package com.fakebank.abc.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
    @Setter
    @Getter
    private Long clientId;
    @Setter
    @Getter
    private int assignedCredit;


}
