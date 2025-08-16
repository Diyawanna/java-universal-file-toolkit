package com.diyawanna.uft.model;

import java.util.Collections;
import java.util.Map;

/**
 * Options for data transformation operations.
 * Allows control over header preservation, row limits, streaming mode, and column mappings.
 */
public final class TransformOptions {
    private final boolean preserveHeaders;
    private final int maxRows;
    private final boolean streamMode;
    private final Map<String, String> columnMappings;
    private final WriteOptions targetWriteOptions;

    private TransformOptions(Builder builder) {
        this.preserveHeaders = builder.preserveHeaders;
        this.maxRows = builder.maxRows;
        this.streamMode = builder.streamMode;
        this.columnMappings = Collections.unmodifiableMap(builder.columnMappings);
        this.targetWriteOptions = builder.targetWriteOptions;
    }

    /**
     * Returns a new builder for creating {@link TransformOptions} instances.
     * @return A new {@link Builder} instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    public boolean isPreserveHeaders() {
        return preserveHeaders;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public boolean isStreamMode() {
        return streamMode;
    }

    public Map<String, String> getColumnMappings() {
        return columnMappings;
    }

    public WriteOptions getTargetWriteOptions() {
        return targetWriteOptions;
    }

    /**
     * Builder class for {@link TransformOptions}.
     */
    public static final class Builder {
        private boolean preserveHeaders = false;
        private int maxRows = -1; // -1 for no limit
        private boolean streamMode = false;
        private Map<String, String> columnMappings = Collections.emptyMap();
        private WriteOptions targetWriteOptions = WriteOptions.builder().build();

        private Builder() {}

        public Builder preserveHeaders(boolean preserveHeaders) {
            this.preserveHeaders = preserveHeaders;
            return this;
        }

        public Builder maxRows(int maxRows) {
            this.maxRows = maxRows;
            return this;
        }

        public Builder streamMode(boolean streamMode) {
            this.streamMode = streamMode;
            return this;
        }

        public Builder columnMappings(Map<String, String> columnMappings) {
            this.columnMappings = columnMappings;
            return this;
        }

        public Builder targetWriteOptions(WriteOptions targetWriteOptions) {
            this.targetWriteOptions = targetWriteOptions;
            return this;
        }

        public TransformOptions build() {
            return new TransformOptions(this);
        }
    }
}


