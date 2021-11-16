package com.ridgue.jangular.exception;

public class EmailAlreadyTakenException extends RuntimeException{
    public EmailAlreadyTakenException() {
        super("Could not Sign Up! This email already exist.");
    }
}
