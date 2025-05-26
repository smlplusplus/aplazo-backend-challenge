package com.fakebank.abc.api.controller;

import com.fakebank.abc.api.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
public class ApiExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setCode("ERR0001");
        error.setError("Something went wrong");
        error.setTimestamp(Instant.now().getEpochSecond());
        error.setMessage("An unexpected error occurred. Please try again later.");
        error.setPath(request.getDescription(false).replace("uri=", ""));
       logger.error(error.toString());
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
