package br.com.marcoshssilva.springbooteureka.controller.exceptions;

public class InternalServerErrorException extends ErrorException {
    public InternalServerErrorException(Object responseBody) {
        super(responseBody);
    }
}
