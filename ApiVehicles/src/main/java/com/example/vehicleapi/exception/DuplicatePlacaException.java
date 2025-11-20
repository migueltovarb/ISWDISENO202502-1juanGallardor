package com.example.vehicleapi.exception;

public class DuplicatePlacaException extends RuntimeException {

    private static final long serialVersionUID = 987654321098765432L;

    public DuplicatePlacaException(String message) {
        super(message);
    }

    public DuplicatePlacaException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatePlacaException(Throwable cause) {
        super(cause);
    }
}
