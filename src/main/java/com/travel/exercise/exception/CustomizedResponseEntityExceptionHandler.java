package com.travel.exercise.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * Exception handler
 * @author diganta
 *
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(FlightNotFoundException.class)
    public final ResponseEntity<Object> flightNotFoundException(FlightNotFoundException flightNotFoundException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Flight Not Found for the provided route");
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
      @ExceptionHandler(ConversionFailedException.class)
      public ResponseEntity<Object> handleConflict(RuntimeException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder sb = new StringBuilder("Please provide following mandatory details ");
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            sb.append("[").append(error.getDefaultMessage()).append("] ");
        }
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), sb.toString());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
                    + violation.getMessage());
        }
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getLocalizedMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({ org.springframework.dao.DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleHibernateConstraintViolation() {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Cannot insert the same record details");
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<Object> handleConverterErrors(JsonMappingException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}