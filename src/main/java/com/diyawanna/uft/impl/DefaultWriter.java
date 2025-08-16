package com.diyawanna.uft.impl;

import com.diyawanna.uft.ToolkitException;
import com.diyawanna.uft.api.Writer;
import com.diyawanna.uft.model.WriteOptions;

import java.io.File;
import java.io.OutputStream;

/**
 * Placeholder default implementation for the {@link Writer} interface.
 * All methods throw {@link ToolkitException} as actual implementation is pending.
 */
public class DefaultWriter implements Writer {

    @Override
    public void write(Object data, File target, WriteOptions options) throws ToolkitException {
        throw new ToolkitException("DefaultWriter.write(Object, File, WriteOptions) not yet implemented.");
    }

    @Override
    public void write(Object data, OutputStream out, WriteOptions options) throws ToolkitException {
        throw new ToolkitException("DefaultWriter.write(Object, OutputStream, WriteOptions) not yet implemented.");
    }
}


