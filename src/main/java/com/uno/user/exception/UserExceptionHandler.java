package com.uno.user.exception;

import org.springframework.beans.BeansException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.uno.user.dto.ErrorResponse;


@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(NumberFormatException exception) {
        return new ResponseEntity<>(new ErrorResponse().setErrorMessage("User Id is not a valid number"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(BeansException.class)
    public ResponseEntity<ErrorResponse> handleBeanMappingException(BeansException exception) {
        return new ResponseEntity<>(new ErrorResponse().setErrorMessage("The bean mapping is not valid"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException exception) {
        return new ResponseEntity<>(new ErrorResponse().setErrorMessage("Unable to add or update the user details"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
