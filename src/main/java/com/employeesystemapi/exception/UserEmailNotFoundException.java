package com.employeesystemapi.exception;


public class UserEmailNotFoundException extends RuntimeException {

    public UserEmailNotFoundException(String exception) {
        super(exception);
    }
}
