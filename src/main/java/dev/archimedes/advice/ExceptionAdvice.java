package dev.archimedes.advice;

import dev.archimedes.utils.exceptions.BadRequest;
import dev.archimedes.utils.exceptions.NullObjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> badRequestHandler(BadRequest badRequest){
        return new ResponseEntity<>(badRequest.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullObjectException.class)
    public ResponseEntity<?> nullObjectHandler(NullObjectException nullObjectException){
        return new ResponseEntity<>(nullObjectException.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedExceptionHandler(AccessDeniedException accessDeniedException){
        return new ResponseEntity<>(accessDeniedException.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
    }
}
