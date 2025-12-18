package br.com.marcoshssilva.springbooteureka.controller.exceptions;

import java.io.Serializable;

@lombok.Getter
@lombok.RequiredArgsConstructor
public class ErrorException extends RuntimeException{
    private final Serializable responseBody;
}
