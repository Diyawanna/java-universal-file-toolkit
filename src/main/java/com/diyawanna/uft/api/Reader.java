package com.diyawanna.uft.api;

import com.diyawanna.uft.ToolkitException;
import com.diyawanna.uft.model.ReadOptions;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Public interface for reading data from various file formats.
 * @param <T> The type of object to be read.
 */
public interface Reader<T> {

    /**
     * Reads data from a specified file.
     * @param source The file to read from.
     * @param options Read options to control the reading process.
     * @return The data read from the file, cast to type T.
     * @throws ToolkitException if an error occurs during reading.
     */
    T read(File source, ReadOptions options) throws ToolkitException;

    /**
     * Reads data from a specified input stream.
     * @param source The input stream to read from.
     * @param options Read options to control the reading process.
     * @return The data read from the stream, cast to type T.
     * @throws ToolkitException if an error occurs during reading.
     */
    T read(InputStream source, ReadOptions options) throws ToolkitException;

    /**
     * Streams tabular data from a specified file. This is particularly useful for large files
     * to avoid loading the entire content into memory.
     * @param source The file to stream from.
     * @param options Read options to control the streaming process.
     * @return A stream of maps, where each map represents a row (column name to value).
     * @throws ToolkitException if an error occurs during streaming.
     */
    Stream<Map<String, Object>> stream(File source, ReadOptions options) throws ToolkitException;
}


