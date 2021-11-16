package com.ridgue.jangular.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("Could not find the resource! Make sure if the given parameter exist.");
    }
}
