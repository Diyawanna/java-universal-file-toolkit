package com.diyawanna.uft.plugin;

import com.diyawanna.uft.api.Reader;
import com.diyawanna.uft.api.Writer;
import com.diyawanna.uft.model.FileFormat;

/**
 * Extension point interface for third-party format handlers.
 * Implementations of this interface can be registered with the UniversalFileToolkit
 * to add support for new file formats.
 */
public interface FormatPlugin {

    /**
     * Checks if this plugin supports the given file format.
     * @param format The {@link FileFormat} to check.
     * @return {@code true} if this plugin supports the format, {@code false} otherwise.
     */
    boolean supports(FileFormat format);

    /**
     * Creates and returns a {@link Reader} instance for the format supported by this plugin.
     * @return A {@link Reader} instance.
     */
    Reader<?> createReader();

    /**
     * Creates and returns a {@link Writer} instance for the format supported by this plugin.
     * @return A {@link Writer} instance.
     */
    Writer createWriter();
}


