package br.com.marcoshssilva.springbooteureka.controller.exceptions;

import java.io.Serializable;

public class InternalServerErrorException extends ErrorException {
    public InternalServerErrorException(Serializable responseBody) {
        super(responseBody);
    }
}
