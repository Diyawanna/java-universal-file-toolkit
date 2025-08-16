package com.diyawanna.uft;

/**
 * Exception thrown when there is an issue with file format parsing or serialization.
 */
public class FormatException extends ToolkitException {

    /**
     * Constructs a new FormatException with the specified detail message.
     * @param message the detail message.
     */
    public FormatException(String message) {
        super(message);
    }

    /**
     * Constructs a new FormatException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause.
     */
    public FormatException(String message, Throwable cause) {
        super(message, cause);
    }
}


