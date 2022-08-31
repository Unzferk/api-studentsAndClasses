package com.api.administration.exceptions;

public class ResourceAHasntResourceBException extends RuntimeException {
    public ResourceAHasntResourceBException(String resourceIdA, String resourceIdB) {
        super("The " +  resourceIdA + " is not registered in " + resourceIdB );
    }
}
