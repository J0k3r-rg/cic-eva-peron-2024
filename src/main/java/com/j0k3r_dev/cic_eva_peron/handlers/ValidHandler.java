package com.j0k3r_dev.cic_eva_peron.handlers;

import com.j0k3r_dev.cic_eva_peron.http.response.ErrorFieldResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validHandler(MethodArgumentNotValidException e){
        List<ErrorFieldResponse> errors = e.getFieldErrors().stream().map(
                objectError -> new ErrorFieldResponse(objectError.getField(), objectError.getDefaultMessage())
        ).toList();
        return ResponseEntity.badRequest().body(errors);
    }
}
