package com.api.administration.exceptions;

public class ResourceAHasResourceBException extends RuntimeException{
    public ResourceAHasResourceBException(String resourceIdA, String resourceIdB) {
        super("The " +  resourceIdA + " is already registered in " + resourceIdB );
    }
}
