package com.gonie.shoppingmall.customExceptions;

public class UsernameNoExistsException extends RuntimeException {
    public UsernameNoExistsException() {
        super();
    }

    public UsernameNoExistsException(String message) {
        super(message);
    }

    public UsernameNoExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}