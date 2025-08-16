package com.diyawanna.uft.api;

import com.diyawanna.uft.ToolkitException;
import com.diyawanna.uft.model.FileFormat;
import com.diyawanna.uft.model.ValidationResult;
import com.diyawanna.uft.model.ValidationOptions;

import java.io.File;

/**
 * Public interface for validating file content against specific rules or schemas.
 */
public interface Validator {

    /**
     * Validates a file against its format-specific rules or a provided schema.
     * @param input The file to validate.
     * @param format The format of the file (e.g., JSON, XML, CSV).
     * @param options Validation options, which may include a schema file, required columns, etc.
     * @return A {@link ValidationResult} indicating whether the file is valid and any errors found.
     * @throws ToolkitException if an error occurs during the validation process (e.g., I/O error, invalid schema).
     */
    ValidationResult validate(File input, FileFormat format, ValidationOptions options) throws ToolkitException;
}


