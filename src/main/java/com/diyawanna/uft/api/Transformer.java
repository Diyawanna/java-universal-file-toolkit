package com.diyawanna.uft.api;

import com.diyawanna.uft.ToolkitException;
import com.diyawanna.uft.model.FileFormat;
import com.diyawanna.uft.model.TransformOptions;

import java.io.File;

/**
 * Public interface for transforming data between different file formats.
 */
public interface Transformer {

    /**
     * Transforms data from a source file in one format to a target file in another format.
     * @param source The source file to read from.
     * @param sourceFormat The format of the source file.
     * @param target The target file to write to.
     * @param targetFormat The desired format for the target file.
     * @param options Transformation options to control the process.
     * @throws ToolkitException if an error occurs during transformation.
     */
    void transform(File source, FileFormat sourceFormat, File target, FileFormat targetFormat, TransformOptions options) throws ToolkitException;
}


