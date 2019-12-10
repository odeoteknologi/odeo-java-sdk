package com.github.odeoteknologi.sdk.util.exception;

public class InvalidStatusCodeException extends Exception {

    public InvalidStatusCodeException(String message) {
        super("InvalidStatusCodeException: " + message);
    }

}
