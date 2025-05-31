package com.ephirious.exception.properties;

public class PropertiesFileException extends RuntimeException {
    public PropertiesFileException(String message) {
        super(message);
    }

    public PropertiesFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
