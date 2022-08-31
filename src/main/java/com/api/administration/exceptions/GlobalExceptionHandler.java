package com.api.administration.exceptions;

import com.api.administration.models.errors.ErrorDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(ResourceAHasntResourceBException.class)
    public ResponseEntity ResourceAHasntResourceBException(ResourceAHasntResourceBException exception){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setMessage(exception.getMessage());
        errorDetail.setTimestamp(System.currentTimeMillis());
        log.error(exception.getMessage());
        return new ResponseEntity(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAHasResourceBException.class)
    public ResponseEntity ResourceAHasResourceBException(ResourceAHasResourceBException exception){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setMessage(exception.getMessage());
        errorDetail.setTimestamp(System.currentTimeMillis());
        log.error(exception.getMessage());
        return new ResponseEntity(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity handlerAlreadyExistException(ResourceAlreadyExistException exception){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setMessage(exception.getMessage());
        errorDetail.setTimestamp(System.currentTimeMillis());
        log.error(exception.getMessage());
        return new ResponseEntity(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handlerNotFoundException(ResourceNotFoundException exception){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setHttpStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(exception.getMessage());
        errorDetail.setTimestamp(System.currentTimeMillis());
        log.error(exception.getMessage());
        return new ResponseEntity(errorDetail, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request){
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
    }
}
