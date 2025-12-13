package br.com.marcoshssilva.springbooteureka.controller.error;

import br.com.marcoshssilva.springbooteureka.controller.exceptions.BadRequestException;
import br.com.marcoshssilva.springbooteureka.controller.exceptions.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControlerAdvice {
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> internalServerErrorExceptionResolver(InternalServerErrorException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getResponseBody());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestExceptionResolver(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getResponseBody());
    }
}
