package com.api.administration.exceptions;

public class ResourceAlreadyExistException extends RuntimeException {
    public ResourceAlreadyExistException(Class klass, String id) {
        super("The " + klass.getSimpleName() + " with ID " + id + " already exist");
    }
    public ResourceAlreadyExistException(Class klass){
        super("The " + klass.getSimpleName() + " was already registered");
    }
}
