package com.api.administration.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Class klass, String id) {
        super("The " + klass.getSimpleName() + " with ID " + id + " was not found");
    }
    public ResourceNotFoundException(Class klass){
        super("The " + klass.getSimpleName() + " was not found");
    }
}
