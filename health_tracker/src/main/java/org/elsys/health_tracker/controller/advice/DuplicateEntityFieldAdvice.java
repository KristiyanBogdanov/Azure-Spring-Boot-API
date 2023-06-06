package org.elsys.health_tracker.controller.advice;

import org.elsys.health_tracker.exception.DuplicateEntityFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DuplicateEntityFieldAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DuplicateEntityFieldException.class)
    public ResponseEntity<?> handleDuplicateEntityFieldException(DuplicateEntityFieldException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
