package com.diyawanna.uft;

/**
 * Exception thrown when a security-related operation (e.g., encryption, decryption, hashing) fails.
 */
public class SecurityException extends ToolkitException {

    /**
     * Constructs a new SecurityException with the specified detail message.
     * @param message the detail message.
     */
    public SecurityException(String message) {
        super(message);
    }

    /**
     * Constructs a new SecurityException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause.
     */
    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }
}


