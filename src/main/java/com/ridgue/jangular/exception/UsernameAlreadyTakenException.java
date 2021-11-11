package com.ridgue.jangular.exception;

public class UsernameAlreadyTakenException extends RuntimeException{
    public UsernameAlreadyTakenException() {
        super("Could not Sign Up! This username already exist.");
    }
}
