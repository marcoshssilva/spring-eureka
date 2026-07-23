package com.github.marcoshssilva.eureka.controller.exceptions;

import java.io.Serializable;

public class BadRequestException extends ErrorException {
    public BadRequestException(Serializable responseBody) {
        super(responseBody);
    }
}
