package com.diyawanna.uft.util;

/**
 * A wrapper class for operation results that includes performance metrics.
 * @param <T> The type of the operation result.
 */
public final class OperationResult<T> {
    private final T result;
    private final PerformanceReport performanceReport;

    /**
     * Constructs a new OperationResult.
     * @param result The actual result of the operation.
     * @param performanceReport The performance report for the operation.
     */
    public OperationResult(T result, PerformanceReport performanceReport) {
        this.result = result;
        this.performanceReport = performanceReport;
    }

    /**
     * Returns the result of the operation.
     * @return The operation result.
     */
    public T getResult() {
        return result;
    }

    /**
     * Returns the performance report for the operation.
     * @return The performance report.
     */
    public PerformanceReport getPerformanceReport() {
        return performanceReport;
    }
}


