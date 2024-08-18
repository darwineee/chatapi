package com.darwin.dev.distributed.exception;

/**
 * This action will be thrown in case: an action should be always success unless server has an unknown error.
 */
public abstract class ActionFailException extends ApiException {
}
