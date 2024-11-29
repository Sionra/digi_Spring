package fr.digi_hello.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VilleAdvice {

    @ExceptionHandler({VilleException.class})
    protected ResponseEntity<String> handleException(VilleException handler) {
        return ResponseEntity.badRequest().body(handler.getMessage());
    }
}
