package com.kdu.smarthome.exception;

public class AddUserException extends RuntimeException {
    public AddUserException() {
        super("Unable to add user");
    }

}
