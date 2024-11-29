package fr.digi_hello.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DepartementAdvice {

    @ExceptionHandler({DepartementException.class})
    protected ResponseEntity<String> handleException(DepartementException handler) {
        return ResponseEntity.badRequest().body(handler.getMessage());
    }
}
