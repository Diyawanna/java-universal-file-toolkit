package com.diyawanna.uft;

/**
 * Exception thrown when an I/O operation fails during file processing.
 */
public class IOProcessingException extends ToolkitException {

    /**
     * Constructs a new IOProcessingException with the specified detail message.
     * @param message the detail message.
     */
    public IOProcessingException(String message) {
        super(message);
    }

    /**
     * Constructs a new IOProcessingException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause.
     */
    public IOProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}


