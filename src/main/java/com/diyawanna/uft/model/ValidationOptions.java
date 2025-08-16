package com.diyawanna.uft.model;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * Options for controlling validation operations.
 * Includes settings for fail-fast behavior, schema files, required columns, and additional properties.
 */
public final class ValidationOptions {
    private final boolean failFast;
    private final Optional<File> schemaFile;
    private final List<String> requiredColumns;
    private final boolean allowAdditionalProperties;

    private ValidationOptions(Builder builder) {
        this.failFast = builder.failFast;
        this.schemaFile = Optional.ofNullable(builder.schemaFile);
        this.requiredColumns = builder.requiredColumns;
        this.allowAdditionalProperties = builder.allowAdditionalProperties;
    }

    /**
     * Returns a new builder for creating {@link ValidationOptions} instances.
     * @return A new {@link Builder} instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    public boolean isFailFast() {
        return failFast;
    }

    public Optional<File> getSchemaFile() {
        return schemaFile;
    }

    public List<String> getRequiredColumns() {
        return requiredColumns;
    }

    public boolean isAllowAdditionalProperties() {
        return allowAdditionalProperties;
    }

    /**
     * Builder class for {@link ValidationOptions}.
     */
    public static final class Builder {
        private boolean failFast = false;
        private File schemaFile = null;
        private List<String> requiredColumns = List.of();
        private boolean allowAdditionalProperties = true;

        private Builder() {}

        public Builder failFast(boolean failFast) {
            this.failFast = failFast;
            return this;
        }

        public Builder schemaFile(File schemaFile) {
            this.schemaFile = schemaFile;
            return this;
        }

        public Builder requiredColumns(List<String> requiredColumns) {
            this.requiredColumns = requiredColumns;
            return this;
        }

        public Builder allowAdditionalProperties(boolean allowAdditionalProperties) {
            this.allowAdditionalProperties = allowAdditionalProperties;
            return this;
        }

        public ValidationOptions build() {
            return new ValidationOptions(this);
        }
    }
}


