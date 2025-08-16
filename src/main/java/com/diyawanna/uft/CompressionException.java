package com.diyawanna.uft;

/**
 * Exception thrown when a compression or decompression operation fails.
 */
public class CompressionException extends ToolkitException {

    /**
     * Constructs a new CompressionException with the specified detail message.
     * @param message the detail message.
     */
    public CompressionException(String message) {
        super(message);
    }

    /**
     * Constructs a new CompressionException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause.
     */
    public CompressionException(String message, Throwable cause) {
        super(message, cause);
    }
}


