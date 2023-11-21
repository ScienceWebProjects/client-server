package com.filament.client.Client.Exception.Handler;

import com.filament.client.Client.Exception.CustomValidationException;
import com.filament.client.Client.Exception.Payload.CustomValidationExceptionPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomValidationErrorHandler {
    @ExceptionHandler(value = {CustomValidationException.class})
    public ResponseEntity<Object> handler(CustomValidationException exception){
        CustomValidationExceptionPayload newException = new CustomValidationExceptionPayload(
                exception.getMessage()
        );
        return new ResponseEntity<>(newException, HttpStatus.BAD_REQUEST);
    }
}
