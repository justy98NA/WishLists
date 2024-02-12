package org.wishlistapp.exception.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wishlistapp.exception.WishListTitleExistsException;

@ControllerAdvice
public class WishListTitleExistsAdvice {
    @ResponseBody
    @ExceptionHandler(WishListTitleExistsException.class)
    String wishListTitleExistsHandler(WishListTitleExistsException ex) {
        return ex.getMessage();
    }
}
