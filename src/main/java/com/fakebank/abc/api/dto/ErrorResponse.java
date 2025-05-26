package com.fakebank.abc.api.dto;

import lombok.Getter;
import lombok.Setter;

public class ErrorResponse {
    @Setter
    @Getter
    private String code;

    @Setter
    @Getter
    private String error;

    @Setter
    @Getter
    private Long timestamp;

    @Setter
    @Getter
    private String message;

    @Setter
    @Getter
    private String path;
}
