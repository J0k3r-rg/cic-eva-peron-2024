package com.j0k3r_dev.cic_eva_peron.handlers;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.j0k3r_dev.cic_eva_peron.http.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class TokenHandlers implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.printf("Pre-handler");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.printf("Post-handler");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.printf("After-handler");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity errorJwtException(JWTDecodeException e){
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .msg("Token no valido")
                        .code(400)
                        .build()
        );
    }
}
