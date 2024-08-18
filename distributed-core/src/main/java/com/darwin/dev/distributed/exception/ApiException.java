package com.darwin.dev.distributed.exception;

/**
 * Base type of all pre-defined checked exception in all services.
 */
public abstract class ApiException extends Exception {
    public abstract int getErrCode();
}
