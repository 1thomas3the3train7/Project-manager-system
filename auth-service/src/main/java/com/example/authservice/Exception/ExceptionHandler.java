package com.example.authservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    private ResponseEntity<Object> constructException(final String message,final HttpStatus httpStatus){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("Timestamp", LocalDateTime.now());
        body.put("Message",message);
        return new ResponseEntity<>(body,httpStatus);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ServiceNotFoundException.class})
    public ResponseEntity<Object> handleServiceNotFoundExc(final Exception e){
        return constructException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotValidRequestException.class})
    public ResponseEntity<Object> handleNotValidRequestException(final Exception e){
        return constructException(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(final Exception e){
        return constructException(e.getMessage(),HttpStatus.NOT_FOUND);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {IncorrectJwtTokenException.class})
    public ResponseEntity<Object> handleIncorrectJwtTokenException(final Exception e){
        return constructException(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
