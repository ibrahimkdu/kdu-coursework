package com.kdu.springboothandson3.exceptions;

public class BadRequestCustomException extends Exception {
    public BadRequestCustomException(String s)
    {
        super(s);
    }
}
