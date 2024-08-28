package com.pasan.task.controller;

import com.pasan.task.beans.dtos.ResponseDto;
import com.pasan.task.beans.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Base Controller for handling exceptions
 */
@ControllerAdvice
public class BaseController {
    private final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ResponseDto<?>> handleNotFoundException(NotFoundException ex, WebRequest request) {
        logger.error("NotFound Exception : [{}], request: [{}]", ex.getMessage(), request);
        logger.error(ex.toString());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseDto.error(ex.getMessage(), ex.getAdditionalInfo()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<?>> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        logger.error("Validation Exception : [{}], request: [{}]", ex.getMessage(), request);
        logger.error(ex.toString());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.error("Validation Error", errors.toString()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseDto<?>> handleException(Exception ex, WebRequest request) {
        logger.error("Exception : [{}], request: [{}]", ex.getMessage(), request);
        logger.error(ex.toString());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDto.error("Internal Server Error", ex.getMessage()));
    }
}
