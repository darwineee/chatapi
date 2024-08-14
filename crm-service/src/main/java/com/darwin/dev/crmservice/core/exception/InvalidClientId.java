package com.darwin.dev.crmservice.core.exception;

public class InvalidClientId extends Exception {
    @Override
    public String getMessage() {
        return "Client ID is not valid";
    }
}
