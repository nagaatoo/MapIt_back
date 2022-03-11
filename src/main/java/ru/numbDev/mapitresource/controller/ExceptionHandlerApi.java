package ru.numbDev.mapitresource.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.numbDev.mapitresource.common.ApiException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerApi extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> allException(Exception ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        if (ex instanceof ApiException exception) {

            body.put("timestamp", LocalDateTime.now());
            body.put("message", exception.getMessage());

            return new ResponseEntity<>(body, HttpStatus.resolve(exception.getCode()));
        } else {

            body.put("timestamp", LocalDateTime.now());
            body.put("message", ex.getMessage());

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
