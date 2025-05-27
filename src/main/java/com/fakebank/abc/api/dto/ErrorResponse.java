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
    private String timestamp;

    @Setter
    @Getter
    private String message;

    @Setter
    @Getter
    private String path;

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code='" + code + '\'' +
                ", error='" + error + '\'' +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
