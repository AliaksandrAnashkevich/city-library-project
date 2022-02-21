package com.solbegsoft.city.library.exception.handler;

import com.solbegsoft.city.library.exception.JwtTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(JwtTokenException.class)
    public ResponseEntity<?> handleJwtToken(JwtTokenException e) {
        log.error("Unauthorized {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
