package com.diyawanna.uft.impl;

import com.diyawanna.uft.ToolkitException;
import com.diyawanna.uft.api.Validator;
import com.diyawanna.uft.model.FileFormat;
import com.diyawanna.uft.model.ValidationResult;
import com.diyawanna.uft.model.ValidationOptions;

import java.io.File;

/**
 * Placeholder default implementation for the {@link Validator} interface.
 * All methods throw {@link ToolkitException} as actual implementation is pending.
 */
public class DefaultValidator implements Validator {

    @Override
    public ValidationResult validate(File input, FileFormat format, ValidationOptions options) throws ToolkitException {
        throw new ToolkitException("DefaultValidator.validate(File, FileFormat, ValidationOptions) not yet implemented.");
    }
}


