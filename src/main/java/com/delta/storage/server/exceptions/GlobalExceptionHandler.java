package com.delta.storage.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    final static String INTERNAL_ERROR = "InternalError";
    final static String INTERNAL_ERROR_MESSAGE = "We encountered an internal error. Please try again.";

    @ExceptionHandler(value = InvalidBucketNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StorageErrorResponse invalidBucketNameException(InvalidBucketNameException ex) {
        return new StorageErrorResponse(ex.getCode(), ex.getMessage(), ex.getResource());
    }

    @ExceptionHandler(value = BucketAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public StorageErrorResponse bucketAlreadyExistsException(BucketAlreadyExistsException ex) {
        return new StorageErrorResponse(ex.getCode(), ex.getMessage(), ex.getResource());
    }

    @ExceptionHandler(value = InternalErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public StorageErrorResponse internalErrorException(InternalErrorException ex) {
        return new StorageErrorResponse(ex.getCode(), ex.getMessage(), ex.getResource());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public StorageErrorResponse unknownException(Exception ex) {
        return new StorageErrorResponse(INTERNAL_ERROR, INTERNAL_ERROR_MESSAGE, "");
    }
}
