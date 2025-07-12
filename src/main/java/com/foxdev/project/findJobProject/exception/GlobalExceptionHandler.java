package com.foxdev.project.findJobProject.exception;

import com.foxdev.project.findJobProject.domain.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IdInvalidException.class, InvalidRequestException.class, UserNotFoundException.class})
    public ResponseEntity<ApiResponse<String>> handleException(RuntimeException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        apiResponse.setMessage(e.getMessage());
        apiResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(UserAlreadyExistedException.class)
    public ResponseEntity<ApiResponse<String>> handleException(UserAlreadyExistedException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(HttpStatus.CONFLICT.value());
        apiResponse.setError(HttpStatus.CONFLICT.getReasonPhrase());
        apiResponse.setMessage(e.getMessage());
        apiResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setError(e.getBody().getDetail());

        List<String> errors = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        apiResponse.setData(errors.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<String>> handleException(BadCredentialsException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setError(e.getMessage());
        apiResponse.setMessage("Bad credentials");
        apiResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
