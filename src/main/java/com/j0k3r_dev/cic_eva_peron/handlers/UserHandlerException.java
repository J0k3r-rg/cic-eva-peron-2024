package com.j0k3r_dev.cic_eva_peron.handlers;

import com.j0k3r_dev.cic_eva_peron.http.response.ErrorResponse;
import com.j0k3r_dev.cic_eva_peron.users.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserHandlerException {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> userException(UserException e){
        return ResponseEntity.status(e.getCode()).body(
                ErrorResponse.builder()
                        .code(e.getCode())
                        .msg(e.getMessage())
                        .build()
        );
    }

}
