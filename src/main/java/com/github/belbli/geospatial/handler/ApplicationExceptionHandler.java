package com.github.belbli.geospatial.handler;

import com.github.belbli.geospatial.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<Object> handle(ValidationException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("error", ex.getClass().getName());
        body.put("path", ((ServletWebRequest) request).getRequest().getServletPath());

        return ResponseEntity.badRequest().body(body);
    }
}
