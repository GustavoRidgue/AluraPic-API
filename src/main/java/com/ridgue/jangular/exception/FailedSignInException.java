package com.ridgue.jangular.exception;

public class FailedSignInException extends RuntimeException{
    public FailedSignInException() {
        super("Could not Sign In! Invalid username and/or password.");
    }
}
