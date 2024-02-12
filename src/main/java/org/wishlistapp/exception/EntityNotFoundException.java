package org.wishlistapp.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, Object identifier) {
        super(entity + " with identifier " + identifier + " not found");
    }
}
