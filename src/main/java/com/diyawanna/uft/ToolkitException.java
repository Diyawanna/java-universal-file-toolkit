package com.diyawanna.uft;

/**
 * Base exception for all errors within the Universal File Toolkit.
 * Provides a consistent way to handle errors with clear messages and cause chaining.
 */
public class ToolkitException extends Exception {

    /**
     * Constructs a new ToolkitException with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause(Throwable)}.
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public ToolkitException(String message) {
        super(message);
    }

    /**
     * Constructs a new ToolkitException with the specified detail message and cause.
     * Note that the detail message associated with {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     *              (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ToolkitException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ToolkitException with the specified cause and a detail message of
     * {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for exceptions that are little more than wrappers for other throwables.
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     *              (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ToolkitException(Throwable cause) {
        super(cause);
    }
}


