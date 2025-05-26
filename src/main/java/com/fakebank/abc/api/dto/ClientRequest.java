package com.fakebank.abc.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
    @Setter @Getter
    private String name;
    @Setter @Getter
    private String birthDate;
}
