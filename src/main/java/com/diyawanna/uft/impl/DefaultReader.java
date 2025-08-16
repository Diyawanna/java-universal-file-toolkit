package com.diyawanna.uft.impl;

import com.diyawanna.uft.ToolkitException;
import com.diyawanna.uft.api.Reader;
import com.diyawanna.uft.model.ReadOptions;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Placeholder default implementation for the {@link Reader} interface.
 * All methods throw {@link ToolkitException} as actual implementation is pending.
 */
public class DefaultReader implements Reader<Object> {

    @Override
    public Object read(File source, ReadOptions options) throws ToolkitException {
        throw new ToolkitException("DefaultReader.read(File, ReadOptions) not yet implemented.");
    }

    @Override
    public Object read(InputStream source, ReadOptions options) throws ToolkitException {
        throw new ToolkitException("DefaultReader.read(InputStream, ReadOptions) not yet implemented.");
    }

    @Override
    public Stream<Map<String, Object>> stream(File source, ReadOptions options) throws ToolkitException {
        throw new ToolkitException("DefaultReader.stream(File, ReadOptions) not yet implemented.");
    }
}


