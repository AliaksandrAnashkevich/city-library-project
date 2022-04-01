package com.solbegsoft.city.library.exception.handler;

import com.solbegsoft.city.library.exception.InvalidAuthRequestDataException;
import com.solbegsoft.city.library.exception.JwtTokenException;
import com.solbegsoft.city.library.exception.UserNotFoundException;
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
        log.error("Bad Request {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidAuthRequestDataException.class)
    public ResponseEntity<?> handleInvalidAuthRequestData(InvalidAuthRequestDataException e) {
        log.error("Unauthorized {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException e) {
        log.error("Not Found {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
