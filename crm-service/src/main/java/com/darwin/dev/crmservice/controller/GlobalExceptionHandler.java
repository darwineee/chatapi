package com.darwin.dev.crmservice.controller;

import com.darwin.dev.distributed.exception.ActionFailException;
import com.darwin.dev.distributed.exception.ApiException;
import com.darwin.dev.distributed.exception.ResourceNotFoundException;
import com.darwin.dev.distributed.util.ErrCode;
import com.darwin.dev.distributed.wrapper.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error<Map<String, String>>> handle(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String message = fieldError.getDefaultMessage();
            String field = fieldError.getField();
            errors.put(field, message);
        });
        var body = new Error<>(ErrCode.FIELD_VALIDATION, errors);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Error<String>> handle(ApiException ex) {
        Error<String> body = new Error<>(
                ex.getErrCode(),
                ex.getMessage()
        );
        if (ex instanceof ResourceNotFoundException) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(body);
        } else if (ex instanceof ActionFailException) {
            return ResponseEntity
                    .internalServerError()
                    .body(body);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(body);
        }
    }
}
