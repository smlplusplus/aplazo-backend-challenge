package com.fakebank.abc.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class CustomerRequest {
    @Setter
    @Getter
    private String firstName;
    @Setter
    @Getter
    private String lastName;
    @Setter
    @Getter
    private String secondLastName;
    @Setter
    @Getter
    private LocalDate dateOfBirth;
}
