package org.bioplat.classifiers.controller.result;

public class Error {

    private final Throwable exception;

    public Error(Throwable exception) {
        this.exception = exception;
    }

    public String getMessage(){
        return exception.getMessage();
    }
}
