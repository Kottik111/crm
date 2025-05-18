package com.example.jpa2.exception;

import com.example.jpa2.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEmployeeNotFound(EmployeeNotFoundException e) {
        ErrorDto errorDto = buildErrorDto(e, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    private static ErrorDto buildErrorDto(Exception e, int code) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        errorDto.setCode(code);
        return errorDto;
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleEmployeeAlreadyExists(EmployeeAlreadyExistsException e) {
        ErrorDto errorDto = buildErrorDto(e, HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorDto> handleInvalidPassword(InvalidPasswordException e) {
        ErrorDto errorDto = buildErrorDto(e, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGeneralException(Exception e) {
        ErrorDto errorDto = buildErrorDto(e, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleClientAlreadyExists(ClientAlreadyExistsException e) {
        ErrorDto errorDto = buildErrorDto(e, HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorDto> handleClientNotFound(ClientNotFoundException e) {
        ErrorDto errorDto = buildErrorDto(e, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorDto> handleOrderNotFound(OrderNotFoundException e) {
        ErrorDto errorDto = buildErrorDto(e, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFound(ProductNotFoundException e) {
        ErrorDto errorDto = buildErrorDto(e, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
