package com.emzon.user.configuration.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.emzon.user.configuration.utilities.constants.ExceptionConstants.*;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(
            IllegalArgumentException ex,
            WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage() != null ? ex.getMessage() : ILLEGAL_ARGUMENT_MESSAGE,
                HTTP_STATUS_BAD_REQUEST,
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllUncaughtException(
            Exception ex,
            WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                UNEXPECTED_ERROR_MESSAGE,
                HTTP_STATUS_INTERNAL_SERVER_ERROR,
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}