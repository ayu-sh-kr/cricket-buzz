package dev.archimedes.advice;

import dev.archimedes.utils.exceptions.BadRequest;
import dev.archimedes.utils.exceptions.NullObjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
