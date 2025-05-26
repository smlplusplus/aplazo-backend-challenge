package com.fakebank.abc.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BnplApiException extends Exception {
    private static final Logger logger = LoggerFactory.getLogger(BnplApiException.class);
    public BnplApiException(String message) {
        super(message);
        logger.error(message);
    }
}
