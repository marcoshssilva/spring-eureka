package br.com.marcoshssilva.springbooteureka.controller.exceptions;

@lombok.Getter
@lombok.RequiredArgsConstructor
public class ErrorException extends RuntimeException{
    private final Object responseBody;
}
