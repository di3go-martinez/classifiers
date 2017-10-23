package org.bioplat.classifiers.controller;

import org.bioplat.classifiers.controller.result.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerAdvice {

    private static Logger logger = LoggerFactory.getLogger(ErrorHandlerAdvice.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleError(Throwable exception){
        logger.error("Error evaluando request", exception);
        return new Error(exception);
    }
}
