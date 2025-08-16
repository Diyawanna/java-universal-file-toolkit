package com.diyawanna.uft.impl;

import com.diyawanna.uft.CompressionException;
import com.diyawanna.uft.api.CompressionService;
import com.diyawanna.uft.model.CompressionType;

import java.io.File;

/**
 * Placeholder default implementation for the {@link CompressionService} interface.
 * All methods throw {@link CompressionException} as actual implementation is pending.
 */
public class DefaultCompressionService implements CompressionService {

    @Override
    public File compress(File source, CompressionType type) throws CompressionException {
        throw new CompressionException("DefaultCompressionService.compress(File, CompressionType) not yet implemented.");
    }

    @Override
    public File decompress(File source) throws CompressionException {
        throw new CompressionException("DefaultCompressionService.decompress(File) not yet implemented.");
    }
}


