package com.j0k3r_dev.cic_eva_peron.handlers;

import com.j0k3r_dev.cic_eva_peron.http.response.ErrorResponse;
import com.j0k3r_dev.cic_eva_peron.users.titular.TitularException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TitularHandlers {

    @ExceptionHandler(TitularException.class)
    public ResponseEntity<?> handleTitularException(TitularException e) {
        return ResponseEntity.status(e.getCode()).body(
                ErrorResponse.builder()
                        .msg(e.getMessage())
                        .code(e.getCode())
                        .build()
        );
    }
}
