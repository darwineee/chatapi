package com.darwin.dev.crmservice.core.exception;

public class InvalidUserId extends Exception {
    @Override
    public String getMessage() {
        return "UserID is invalid";
    }
}
