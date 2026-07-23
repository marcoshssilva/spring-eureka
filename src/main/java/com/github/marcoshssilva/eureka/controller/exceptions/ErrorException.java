package com.github.marcoshssilva.eureka.controller.exceptions;

import java.io.Serializable;

public class ErrorException extends RuntimeException{
    private final Serializable responseBody;

    public ErrorException(Serializable responseBody) {
        this.responseBody = responseBody;
    }

    public Serializable getResponseBody() {
        return responseBody;
    }
}
