package com.ridgue.jangular.exception;

public class InvalidFieldDataException extends RuntimeException{
    public InvalidFieldDataException() {
        super("Invalid field data! Make sure the data size.");
    }
}
