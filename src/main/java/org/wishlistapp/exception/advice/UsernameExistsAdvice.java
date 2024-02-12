package org.wishlistapp.exception.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.wishlistapp.exception.UsernameExistsException;

@ControllerAdvice
public class UsernameExistsAdvice {
    @ResponseBody
    @ExceptionHandler(UsernameExistsException.class)
    String usernameExistsHandler(UsernameExistsException ex) {
        return ex.getMessage();
    }
}
