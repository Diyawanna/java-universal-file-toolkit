package com.diyawanna.uft.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Internal logging utility for the Universal File Toolkit.
 * It wraps an SLF4J Logger and respects the configured logging level.
 */
public class ToolkitLogger {

    private final Logger logger;
    private final boolean loggingEnabled;
    private final LogLevel logLevel;

    /**
     * Constructs a ToolkitLogger instance.
     * @param loggingEnabled True if logging is globally enabled.
     * @param logLevel The minimum level for messages to be logged.
     * @param externalLogger An optional external SLF4J Logger to use. If null, a default logger for this class is created.
     */
    public ToolkitLogger(boolean loggingEnabled, LogLevel logLevel, Logger externalLogger) {
        this.loggingEnabled = loggingEnabled;
        this.logLevel = logLevel;
        this.logger = (externalLogger != null) ? externalLogger : LoggerFactory.getLogger(ToolkitLogger.class);
    }

    /**
     * Logs an informational message.
     * @param message The message to log.
     */
    public void info(String message) {
        if (loggingEnabled && (logLevel.ordinal() >= LogLevel.INFO.ordinal())) {
            logger.info(message);
        }
    }

    /**
     * Logs a debug message.
     * @param message The message to log.
     */
    public void debug(String message) {
        if (loggingEnabled && (logLevel.ordinal() >= LogLevel.DEBUG.ordinal())) {
            logger.debug(message);
        }
    }

    /**
     * Logs an error message.
     * @param message The message to log.
     */
    public void error(String message) {
        if (loggingEnabled && (logLevel.ordinal() >= LogLevel.ERROR.ordinal())) {
            logger.error(message);
        }
    }

    /**
     * Logs an error message with a throwable.
     * @param message The message to log.
     * @param throwable The throwable to log.
     */
    public void error(String message, Throwable throwable) {
        if (loggingEnabled && (logLevel.ordinal() >= LogLevel.ERROR.ordinal())) {
            logger.error(message, throwable);
        }
    }
}


