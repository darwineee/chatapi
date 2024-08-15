package com.darwin.dev.distributed.exception;

public abstract class ApiException extends Exception {
    public abstract int getErrCode();
}
