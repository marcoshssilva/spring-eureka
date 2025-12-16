package br.com.marcoshssilva.springbooteureka.controller.error;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.StatusTypeResponse;
import br.com.marcoshssilva.springbooteureka.controller.data.responses.ErrorStatusResponseBodyDto;
import br.com.marcoshssilva.springbooteureka.controller.data.responses.SimpleStatusResponseBodyDto;
import br.com.marcoshssilva.springbooteureka.controller.exceptions.BadRequestException;
import br.com.marcoshssilva.springbooteureka.controller.exceptions.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

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

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<Object> httpMessageConversionExceptionResolver(HttpMessageConversionException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimpleStatusResponseBodyDto("Invalid or corrupted payload", StatusTypeResponse.ERROR));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validationExceptionResolver(MethodArgumentNotValidException e) {
        ErrorStatusResponseBodyDto err = new ErrorStatusResponseBodyDto("Invalid argument data in payload.", e.getFieldErrors().stream().map(fieldError -> new ErrorStatusResponseBodyDto.ErrorField(fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getRejectedValue())).collect(Collectors.toSet()), StatusTypeResponse.ERROR);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> noResourceFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SimpleStatusResponseBodyDto(e.getMessage(), StatusTypeResponse.ERROR));
    }
}
