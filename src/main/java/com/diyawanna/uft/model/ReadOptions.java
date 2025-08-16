package com.diyawanna.uft.model;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.io.File;

/**
 * Options for controlling how data is read from files.
 * Includes settings for character encoding, CSV delimiters, and schema files.
 */
public final class ReadOptions {
    private final Charset charset;
    private final String csvDelimiter;
    private final boolean csvHasHeader;
    private final boolean streamMode;
    private final Optional<File> schemaFile; // For JSON schema / XSD
    private final boolean allowAdditionalProperties; // For JSON

    private ReadOptions(Builder builder) {
        this.charset = builder.charset;
        this.csvDelimiter = builder.csvDelimiter;
        this.csvHasHeader = builder.csvHasHeader;
        this.streamMode = builder.streamMode;
        this.schemaFile = Optional.ofNullable(builder.schemaFile);
        this.allowAdditionalProperties = builder.allowAdditionalProperties;
    }

    /**
     * Returns a new builder for creating {@link ReadOptions} instances.
     * @return A new {@link Builder} instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    public Charset getCharset() {
        return charset;
    }

    public String getCsvDelimiter() {
        return csvDelimiter;
    }

    public boolean isCsvHasHeader() {
        return csvHasHeader;
    }

    public boolean isStreamMode() {
        return streamMode;
    }

    public Optional<File> getSchemaFile() {
        return schemaFile;
    }

    public boolean isAllowAdditionalProperties() {
        return allowAdditionalProperties;
    }

    /**
     * Builder class for {@link ReadOptions}.
     */
    public static final class Builder {
        private Charset charset = StandardCharsets.UTF_8;
        private String csvDelimiter = ",";
        private boolean csvHasHeader = true;
        private boolean streamMode = false;
        private File schemaFile = null;
        private boolean allowAdditionalProperties = true;

        private Builder() {}

        public Builder charset(Charset charset) {
            this.charset = charset;
            return this;
        }

        public Builder csvDelimiter(String csvDelimiter) {
            this.csvDelimiter = csvDelimiter;
            return this;
        }

        public Builder csvHasHeader(boolean csvHasHeader) {
            this.csvHasHeader = csvHasHeader;
            return this;
        }

        public Builder streamMode(boolean streamMode) {
            this.streamMode = streamMode;
            return this;
        }

        public Builder schemaFile(File schemaFile) {
            this.schemaFile = schemaFile;
            return this;
        }

        public Builder allowAdditionalProperties(boolean allowAdditionalProperties) {
            this.allowAdditionalProperties = allowAdditionalProperties;
            return this;
        }

        public ReadOptions build() {
            return new ReadOptions(this);
        }
    }
}


