package com.diyawanna.uft.impl;

import com.diyawanna.uft.ToolkitException;
import com.diyawanna.uft.api.Transformer;
import com.diyawanna.uft.model.FileFormat;
import com.diyawanna.uft.model.TransformOptions;

import java.io.File;

/**
 * Placeholder default implementation for the {@link Transformer} interface.
 * All methods throw {@link ToolkitException} as actual implementation is pending.
 */
public class DefaultTransformer implements Transformer {

    @Override
    public void transform(File source, FileFormat sourceFormat, File target, FileFormat targetFormat, TransformOptions options) throws ToolkitException {
        throw new ToolkitException("DefaultTransformer.transform(File, FileFormat, File, FileFormat, TransformOptions) not yet implemented.");
    }
}


