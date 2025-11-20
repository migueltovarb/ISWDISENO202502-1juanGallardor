package com.example.vehicleapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVehicleNotFound(VehicleNotFoundException ex) {
        ErrorResponse error = this.buildErrorResponse(
            "VEHICLE_NOT_FOUND",
            ex.getMessage(),
            HttpStatus.NOT_FOUND
        );
        return this.buildResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatePlacaException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatePlaca(DuplicatePlacaException ex) {
        ErrorResponse error = this.buildErrorResponse(
            "DUPLICATE_PLACA",
            ex.getMessage(),
            HttpStatus.CONFLICT
        );
        return this.buildResponseEntity(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> validationErrors = ex.getBindingResult()
            .getAllErrors()
            .stream()
            .collect(Collectors.toMap(
                error -> ((FieldError) error).getField(),
                error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Error de validación",
                (existing, replacement) -> existing
            ));

        ErrorResponse error = this.buildErrorResponse(
            "VALIDATION_ERROR",
            "Errores de validación en los datos enviados",
            HttpStatus.BAD_REQUEST
        );
        error.setValidationErrors(validationErrors);

        return this.buildResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        String errorMessage = String.format("Error interno del servidor: %s", ex.getMessage());
        ErrorResponse error = this.buildErrorResponse(
            "INTERNAL_SERVER_ERROR",
            errorMessage,
            HttpStatus.INTERNAL_SERVER_ERROR
        );
        return this.buildResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse buildErrorResponse(String code, String message, HttpStatus status) {
        return new ErrorResponse(code, message, status.value(), LocalDateTime.now());
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(ErrorResponse error, HttpStatus status) {
        return ResponseEntity.status(status).body(error);
    }

    public static class ErrorResponse {
        private String errorCode;
        private String message;
        private int status;
        private LocalDateTime timestamp;
        private Map<String, String> validationErrors;

        public ErrorResponse(String errorCode, String message, int status, LocalDateTime timestamp) {
            this.errorCode = errorCode;
            this.message = message;
            this.status = status;
            this.timestamp = timestamp;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public Map<String, String> getValidationErrors() {
            return validationErrors;
        }

        public void setValidationErrors(Map<String, String> validationErrors) {
            this.validationErrors = validationErrors;
        }
    }
}
