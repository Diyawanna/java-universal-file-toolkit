package com.diyawanna.uft;

import com.diyawanna.uft.model.EncryptionType;
import com.diyawanna.uft.util.LogLevel;
import org.slf4j.Logger;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Configuration class for the Universal File Toolkit. 
 * Allows enabling/disabling logging, performance tracking, setting default encryption, etc.
 * Instances are immutable and built using a {@link Builder}.
 */
public final class ToolkitConfig {
    private final boolean loggingEnabled;
    private final boolean performanceTrackingEnabled;
    private final LogLevel logLevel;
    private final EncryptionType defaultEncryption;
    private final Charset defaultCharset;
    private final Optional<Logger> externalLogger;

    /**
     * Private constructor to enforce the use of the Builder.
     * @param builder The builder instance containing the configuration.
     */
    private ToolkitConfig(Builder builder) {
        this.loggingEnabled = builder.loggingEnabled;
        this.performanceTrackingEnabled = builder.performanceTrackingEnabled;
        this.logLevel = builder.logLevel;
        this.defaultEncryption = builder.defaultEncryption;
        this.defaultCharset = builder.defaultCharset;
        this.externalLogger = Optional.ofNullable(builder.externalLogger);
    }

    /**
     * Returns a new builder for creating {@link ToolkitConfig} instances.
     * @return A new {@link Builder} instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Checks if logging is enabled.
     * @return {@code true} if logging is enabled, {@code false} otherwise.
     */
    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    /**
     * Checks if performance tracking is enabled.
     * @return {@code true} if performance tracking is enabled, {@code false} otherwise.
     */
    public boolean isPerformanceTrackingEnabled() {
        return performanceTrackingEnabled;
    }

    /**
     * Returns the configured log level.
     * @return The {@link LogLevel}.
     */
    public LogLevel getLogLevel() {
        return logLevel;
    }

    /**
     * Returns the default encryption type.
     * @return The {@link EncryptionType}, or {@code null} if not set.
     */
    public EncryptionType getDefaultEncryption() {
        return defaultEncryption;
    }

    /**
     * Returns the default character set for file operations.
     * @return The default {@link Charset}.
     */
    public Charset getDefaultCharset() {
        return defaultCharset;
    }

    /**
     * Returns an {@link Optional} containing the external logger if set, otherwise an empty Optional.
     * @return An {@link Optional} of {@link Logger}.
     */
    public Optional<Logger> getExternalLogger() {
        return externalLogger;
    }

    /**
     * Builder class for {@link ToolkitConfig}.
     */
    public static final class Builder {
        private boolean loggingEnabled = false;
        private boolean performanceTrackingEnabled = false;
        private LogLevel logLevel = LogLevel.INFO;
        private EncryptionType defaultEncryption = null;
        private Charset defaultCharset = StandardCharsets.UTF_8;
        private Logger externalLogger;

        private Builder() {
            // Private constructor
        }

        /**
         * Sets whether logging should be enabled.
         * @param value {@code true} to enable logging, {@code false} to disable.
         * @return The builder instance.
         */
        public Builder enableLogging(boolean value) {
            this.loggingEnabled = value;
            return this;
        }

        /**
         * Sets whether performance tracking should be enabled.
         * @param value {@code true} to enable performance tracking, {@code false} to disable.
         * @return The builder instance.
         */
        public Builder enablePerformanceTracking(boolean value) {
            this.performanceTrackingEnabled = value;
            return this;
        }

        /**
         * Sets the default log level.
         * @param level The {@link LogLevel} to set.
         * @return The builder instance.
         */
        public Builder logLevel(LogLevel level) {
            this.logLevel = level;
            return this;
        }

        /**
         * Sets the default encryption type.
         * @param type The {@link EncryptionType} to set as default.
         * @return The builder instance.
         */
        public Builder defaultEncryption(EncryptionType type) {
            this.defaultEncryption = type;
            return this;
        }

        /**
         * Sets the default character set for file operations.
         * @param charset The {@link Charset} to set as default.
         * @return The builder instance.
         */
        public Builder defaultCharset(Charset charset) {
            this.defaultCharset = charset;
            return this;
        }

        /**
         * Sets an external SLF4J {@link Logger} to be used by the toolkit.
         * @param logger The {@link Logger} instance.
         * @return The builder instance.
         */
        public Builder externalLogger(Logger logger) {
            this.externalLogger = logger;
            return this;
        }

        /**
         * Builds an immutable {@link ToolkitConfig} instance.
         * @return A new {@link ToolkitConfig} instance.
         */
        public ToolkitConfig build() {
            return new ToolkitConfig(this);
        }
    }
}


