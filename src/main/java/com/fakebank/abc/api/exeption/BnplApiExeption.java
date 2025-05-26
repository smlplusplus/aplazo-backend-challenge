package com.fakebank.abc.api.exeption;

public class BnplApiExeption extends RuntimeException {
    public BnplApiExeption(String message) {
        super(message);
    }
}
