package com.github.marcoshssilva.eureka.controller.exceptions;

import java.io.Serializable;

public class InternalServerErrorException extends ErrorException {
    public InternalServerErrorException(Serializable responseBody) {
        super(responseBody);
    }
}
