package com.seliganoacai.acai.webConfig.configException;

import com.seliganoacai.acai.exception.UserNotFoundException;
import com.seliganoacai.acai.exception.UsernameUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity<CustomMessageException> usernameUniqueViolationException(UsernameUniqueViolationException ex, HttpServletRequest request){

        logger.error("Conflito por duplicação de username", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON)
                .body(new CustomMessageException(request, HttpStatus.CONFLICT , ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomMessageException> userNotFoundException(UserNotFoundException ex, HttpServletRequest request){

        logger.error("Login ou senhas incorreta", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                .body(new CustomMessageException(request, HttpStatus.NOT_FOUND , ex.getMessage()));
    }


}
