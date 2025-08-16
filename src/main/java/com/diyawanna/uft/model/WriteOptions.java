package com.diyawanna.uft.model;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Options for controlling how data is written to files.
 * Includes settings for character encoding, CSV delimiters, pretty printing, and compression.
 */
public final class WriteOptions {
    private final Charset charset;
    private final String csvDelimiter;
    private final boolean csvHasHeader;
    private final String dateFormat;
    private final boolean prettyPrintJson;
    private final CompressionType compressWith;

    private WriteOptions(Builder builder) {
        this.charset = builder.charset;
        this.csvDelimiter = builder.csvDelimiter;
        this.csvHasHeader = builder.csvHasHeader;
        this.dateFormat = builder.dateFormat;
        this.prettyPrintJson = builder.prettyPrintJson;
        this.compressWith = builder.compressWith;
    }

    /**
     * Returns a new builder for creating {@link WriteOptions} instances.
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

    public String getDateFormat() {
        return dateFormat;
    }

    public boolean isPrettyPrintJson() {
        return prettyPrintJson;
    }

    public CompressionType getCompressWith() {
        return compressWith;
    }

    /**
     * Builder class for {@link WriteOptions}.
     */
    public static final class Builder {
        private Charset charset = StandardCharsets.UTF_8;
        private String csvDelimiter = ",";
        private boolean csvHasHeader = true;
        private String dateFormat = null;
        private boolean prettyPrintJson = false;
        private CompressionType compressWith = CompressionType.NONE;

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

        public Builder dateFormat(String dateFormat) {
            this.dateFormat = dateFormat;
            return this;
        }

        public Builder prettyPrintJson(boolean prettyPrintJson) {
            this.prettyPrintJson = prettyPrintJson;
            return this;
        }

        public Builder compressWith(CompressionType compressWith) {
            this.compressWith = compressWith;
            return this;
        }

        public WriteOptions build() {
            return new WriteOptions(this);
        }
    }
}


