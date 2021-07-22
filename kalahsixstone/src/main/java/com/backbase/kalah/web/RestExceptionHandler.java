package com.backbase.kalah.web;

import com.backbase.kalah.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = KalahGameException.class)
    public ResponseEntity<ApiError> handleKalahGameException(KalahGameException kge){
        ApiError apiError = new ApiError(kge.getErrorcode(), kge.getErrormessage());
        return ResponseEntity.status(kge.getErrorcode()).body(apiError);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex){
        ApiError apiError = new ApiError(500, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }
}
