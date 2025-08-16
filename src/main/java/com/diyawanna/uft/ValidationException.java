package com.diyawanna.uft;

/**
 * Exception thrown when validation of file content or structure fails.
 */
public class ValidationException extends ToolkitException {

    /**
     * Constructs a new ValidationException with the specified detail message.
     * @param message the detail message.
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Constructs a new ValidationException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause.
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}


