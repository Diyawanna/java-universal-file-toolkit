package com.diyawanna.uft.model;

import java.util.Collections;
import java.util.List;

/**
 * Represents the result of a validation operation.
 * Contains a boolean indicating validity and a list of errors if any.
 */
public final class ValidationResult {
    private final boolean valid;
    private final List<ValidationError> errors;

    /**
     * Constructs a new ValidationResult.
     * @param valid True if the validation passed, false otherwise.
     * @param errors A list of validation errors. Should be empty if valid is true.
     */
    public ValidationResult(boolean valid, List<ValidationError> errors) {
        this.valid = valid;
        this.errors = Collections.unmodifiableList(errors);
    }

    /**
     * Checks if the validation was successful.
     * @return True if valid, false otherwise.
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Returns the list of validation errors.
     * @return An unmodifiable list of {@link ValidationError} objects. Will be empty if no errors.
     */
    public List<ValidationError> getErrors() {
        return errors;
    }

    /**
     * Convenience method to create a successful validation result.
     * @return A valid {@link ValidationResult}.
     */
    public static ValidationResult valid() {
        return new ValidationResult(true, Collections.emptyList());
    }

    /**
     * Convenience method to create a failed validation result with errors.
     * @param errors The list of errors that caused the validation to fail.
     * @return An invalid {@link ValidationResult}.
     */
    public static ValidationResult invalid(List<ValidationError> errors) {
        return new ValidationResult(false, errors);
    }
}


