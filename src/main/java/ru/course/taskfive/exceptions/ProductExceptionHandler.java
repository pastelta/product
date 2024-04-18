package ru.course.taskfive.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();

        List<String> listErrors = new ArrayList<>();

        for(FieldError fieldError:fieldErrorList){
            String errorMessage = fieldError.getDefaultMessage();
            listErrors.add(errorMessage);
        }

        responseBody.put("errors", listErrors);

        return new ResponseEntity<>(responseBody, headers, status);
    }

}
