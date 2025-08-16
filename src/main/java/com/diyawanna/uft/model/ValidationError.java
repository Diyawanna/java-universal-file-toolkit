package com.diyawanna.uft.model;

/**
 * Represents a single validation error, including its path, message, and severity.
 */
public final class ValidationError {
    private final String path;
    private final String message;
    private final ValidationSeverity severity;

    /**
     * Constructs a new ValidationError.
     * @param path The path to the element that caused the error (e.g., "$.items[2].price").
     * @param message A descriptive message about the error.
     * @param severity The severity level of the error (ERROR or WARNING).
     */
    public ValidationError(String path, String message, ValidationSeverity severity) {
        this.path = path;
        this.message = message;
        this.severity = severity;
    }

    /**
     * Returns the path to the element that caused the error.
     * @return The error path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns the descriptive message about the error.
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the severity level of the error.
     * @return The {@link ValidationSeverity}.
     */
    public ValidationSeverity getSeverity() {
        return severity;
    }

    @Override
    public String toString() {
        return "ValidationError{" +
               "path=\'" + path + '\'' +
               ", message=\'" + message + '\'' +
               ", severity=" + severity +
               '}';
    }
}


