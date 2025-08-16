package com.diyawanna.uft.api;

import com.diyawanna.uft.CompressionException;
import com.diyawanna.uft.model.CompressionType;

import java.io.File;

/**
 * Public interface for performing compression and decompression operations.
 */
public interface CompressionService {

    /**
     * Compresses a given file using the specified compression type.
     * @param source The file to compress.
     * @param type The type of compression to apply (e.g., GZIP, ZIP).
     * @return The compressed file.
     * @throws CompressionException if an error occurs during compression.
     */
    File compress(File source, CompressionType type) throws CompressionException;

    /**
     * Decompresses a given file. The implementation should ideally auto-detect the compression type.
     * @param source The file to decompress.
     * @return The decompressed file (or a directory for ZIP with multiple entries).
     * @throws CompressionException if an error occurs during decompression.
     */
    File decompress(File source) throws CompressionException;
}


