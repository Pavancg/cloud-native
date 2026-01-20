package com.pavan.auth.exceptions;

public class UserAlreadyExistsException extends UserRegistrationException{

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
