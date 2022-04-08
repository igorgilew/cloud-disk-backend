package com.storage.cloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityConstraintException extends ResponseStatusException {

    public EntityConstraintException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
