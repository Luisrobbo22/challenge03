package br.com.fiap.challenge03.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ValidationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<ValidationFieldError> handle(MethodArgumentNotValidException e) {
        log.info("um erro de validação aconteceu");
        List<ValidationFieldError> list = new ArrayList<>();

        List<FieldError> errors = e.getFieldErrors(); // getBindingResult().getFieldErrors();

        errors.forEach(error -> {
            list.add(new ValidationFieldError(
                    error.getField(),
                    error.getDefaultMessage()));
        });


        return list;
    }


//    @ExceptionHandler(Exception.class)
//    public void handle() {
//        System.out.println("-------------------um erro aconteceu");
//    }
}

