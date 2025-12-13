package br.com.marcoshssilva.springbooteureka.controller.exceptions;

public class BadRequestException extends ErrorException {
    public BadRequestException(Object responseBody) {
        super(responseBody);
    }
}
