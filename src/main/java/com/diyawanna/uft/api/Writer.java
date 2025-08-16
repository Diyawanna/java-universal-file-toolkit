package com.diyawanna.uft.api;

import com.diyawanna.uft.ToolkitException;
import com.diyawanna.uft.model.WriteOptions;

import java.io.File;
import java.io.OutputStream;

/**
 * Public interface for writing data to various file formats.
 */
public interface Writer {

    /**
     * Writes data to a specified file.
     * @param data The object containing the data to write.
     * @param target The file to write to.
     * @param options Write options to control the writing process.
     * @throws ToolkitException if an error occurs during writing.
     */
    void write(Object data, File target, WriteOptions options) throws ToolkitException;

    /**
     * Writes data to a specified output stream.
     * @param data The object containing the data to write.
     * @param out The output stream to write to.
     * @param options Write options to control the writing process.
     * @throws ToolkitException if an error occurs during writing.
     */
    void write(Object data, OutputStream out, WriteOptions options) throws ToolkitException;
}


