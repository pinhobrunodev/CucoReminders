package com.ucsal.cucoreminders.controllers.exceptions;

import com.ucsal.cucoreminders.services.exceptions.ForbiddenException;
import com.ucsal.cucoreminders.services.exceptions.ResourceNotFoundException;
import com.ucsal.cucoreminders.services.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError();
        error.setMoment(Instant.now());
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setError("Resource not found");
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError();
        error.setMoment(Instant.now());
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setError("Erro de validação");
        error.setPath(request.getRequestURI());
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            error.add(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(error);
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<OAuthCustomError> unauthorized (UnauthorizedException e){
        OAuthCustomError error = new OAuthCustomError();
        error.setError("Unauthorized");
        error.setErrorDescription(e.getMessage());
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<OAuthCustomError> forbidden (ForbiddenException e){
        OAuthCustomError error = new OAuthCustomError();
        error.setError("Access Denied");
        error.setErrorDescription(e.getMessage());
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}