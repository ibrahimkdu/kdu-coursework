package com.caching.exception;

public class ApiNullResponse extends RuntimeException {
    public ApiNullResponse(String message) {
        super(message);
    }
}