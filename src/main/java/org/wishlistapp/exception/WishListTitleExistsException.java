package org.wishlistapp.exception;

public class WishListTitleExistsException extends RuntimeException{
    public WishListTitleExistsException(String title) {
        super("WishList with title " + title + " already exists");
    }
}
