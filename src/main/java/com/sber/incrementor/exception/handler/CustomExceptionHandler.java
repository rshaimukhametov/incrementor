package com.sber.incrementor.exception.handler;

import com.sber.incrementor.exception.model.InvalidMaxValueException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidMaxValueException.class)
    private ResponseEntity<Object> handleMaxValueException(InvalidMaxValueException exception) {
        log.error("InvalidMaxValueException occurred: {}", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

}
