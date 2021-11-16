package com.ridgue.jangular.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
        super("You are not authorized to do this! Make sure if you have access or you have a valid token.");
    }
}
