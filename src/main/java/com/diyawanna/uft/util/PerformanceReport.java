package com.diyawanna.uft.util;

import java.util.Collections;
import java.util.Map;

/**
 * Represents a report of performance metrics for an operation.
 * Includes elapsed time, bytes read/written, peak memory usage, and stage timings.
 */
public final class PerformanceReport {
    private final long elapsedMillis;
    private final long bytesRead;
    private final long bytesWritten;
    private final long peakMemoryBytes;
    private final Map<String, Object> stageTimings;

    /**
     * Constructs a new PerformanceReport.
     * @param elapsedMillis The total elapsed time for the operation in milliseconds.
     * @param bytesRead The number of bytes read during the operation.
     * @param bytesWritten The number of bytes written during the operation.
     * @param peakMemoryBytes The peak memory usage in bytes during the operation (best-effort).
     * @param stageTimings A map of timings for different stages of the operation.
     */
    public PerformanceReport(long elapsedMillis, long bytesRead, long bytesWritten, long peakMemoryBytes, Map<String, Object> stageTimings) {
        this.elapsedMillis = elapsedMillis;
        this.bytesRead = bytesRead;
        this.bytesWritten = bytesWritten;
        this.peakMemoryBytes = peakMemoryBytes;
        this.stageTimings = Collections.unmodifiableMap(stageTimings);
    }

    public long getElapsedMillis() {
        return elapsedMillis;
    }

    public long getBytesRead() {
        return bytesRead;
    }

    public long getBytesWritten() {
        return bytesWritten;
    }

    public long getPeakMemoryBytes() {
        return peakMemoryBytes;
    }

    public Map<String, Object> getStageTimings() {
        return stageTimings;
    }

    @Override
    public String toString() {
        return "PerformanceReport{" +
               "elapsedMillis=" + elapsedMillis +
               ", bytesRead=" + bytesRead +
               ", bytesWritten=" + bytesWritten +
               ", peakMemoryBytes=" + peakMemoryBytes +
               ", stageTimings=" + stageTimings +
               '}';
    }
}


