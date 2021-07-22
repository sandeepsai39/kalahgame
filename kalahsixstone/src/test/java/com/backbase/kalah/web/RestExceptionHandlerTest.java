package com.backbase.kalah.web;

import com.backbase.kalah.dto.ApiError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RestExceptionHandlerTest {

    @InjectMocks
    RestExceptionHandler mockHandler;

    @Test
    public void testHandleKalahGameException(){
        KalahGameException exception = new KalahGameException(400,"Business validation failed","DB");
        ResponseEntity<ApiError> errorResponse = mockHandler.handleKalahGameException(exception);
        assertEquals(errorResponse.getBody().getErrorCode(),400);
    }

    @Test
    public void testException(){
        Exception exception = new Exception("Internal Server Error");
        ResponseEntity<ApiError> errorResponse = mockHandler.handleException(exception);
        assertEquals(errorResponse.getBody().getErrorCode(),500);
    }
}
