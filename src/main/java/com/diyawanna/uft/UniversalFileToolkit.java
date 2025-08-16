package com.diyawanna.uft;

import com.diyawanna.uft.model.*;
import com.diyawanna.uft.util.ToolkitLogger;
import com.diyawanna.uft.api.Reader;
import com.diyawanna.uft.api.Writer;
import com.diyawanna.uft.api.Transformer;
import com.diyawanna.uft.api.Validator;
import com.diyawanna.uft.api.SecurityService;
import com.diyawanna.uft.api.CompressionService;
import com.diyawanna.uft.impl.*; // Import all default implementations

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Primary facade for the Universal File Toolkit. Provides a single entry point
 * for reading, writing, transforming, validating, encrypting, and compressing
 * various file formats.
 */
public final class UniversalFileToolkit {

    private final ToolkitConfig config;
    private final ToolkitLogger logger;

    // Internal service implementations
    private final Reader<Object> reader;
    private final Writer writer;
    private final Transformer transformer;
    private final Validator validator;
    private final SecurityService securityService;
    private final CompressionService compressionService;

    /**
     * Constructs a new UniversalFileToolkit instance with the given configuration.
     * @param config The configuration for the toolkit.
     */
    public UniversalFileToolkit(ToolkitConfig config) {
        this.config = config;
        this.logger = new ToolkitLogger(config.isLoggingEnabled(), config.getLogLevel(), config.getExternalLogger().orElse(null));

        // Initialize internal services with default implementations
        this.reader = new DefaultReader();
        this.writer = new DefaultWriter();
        this.transformer = new DefaultTransformer();
        this.validator = new DefaultValidator();
        this.securityService = new DefaultSecurityService();
        this.compressionService = new DefaultCompressionService();

        logger.info("UniversalFileToolkit initialized with config: " + config);
    }

    /**
     * Reads a file of a specified format into a target type.
     * @param source The source file to read.
     * @param format The format of the source file.
     * @param targetType The class of the target type to convert the file content into.
     * @param <T> The type of the object to return.
     * @return The file content converted to the target type.
     * @throws ToolkitException if an error occurs during reading or conversion.
     */
    public <T> T read(File source, FileFormat format, Class<T> targetType) throws ToolkitException {
        logger.debug("Reading file: " + source.getAbsolutePath() + " as " + format + " into " + targetType.getName());
        // In a real implementation, you would select the appropriate reader based on 'format'
        // For now, we'll use the default reader and cast the result (which will throw an exception)
        return (T) reader.read(source, ReadOptions.builder().build());
    }

    /**
     * Reads an input stream of a specified format into a target type.
     * @param sourceStream The input stream to read.
     * @param format The format of the source stream.
     * @param targetType The class of the target type to convert the stream content into.
     * @param <T> The type of the object to return.
     * @return The stream content converted to the target type.
     * @throws ToolkitException if an error occurs during reading or conversion.
     */
    public <T> T read(InputStream sourceStream, FileFormat format, Class<T> targetType) throws ToolkitException {
        logger.debug("Reading input stream as " + format + " into " + targetType.getName());
        return (T) reader.read(sourceStream, ReadOptions.builder().build());
    }

    /**
     * Reads a file of a specified format into a list of maps, typically for tabular data.
     * @param source The source file to read.
     * @param format The format of the source file (e.g., CSV, EXCEL).
     * @return A list of maps, where each map represents a row and keys are column names.
     * @throws ToolkitException if an error occurs during reading.
     */
    public List<Map<String, Object>> readAsTable(File source, FileFormat format) throws ToolkitException {
        logger.debug("Reading file: " + source.getAbsolutePath() + " as table from " + format);
        // This would typically use a specific reader for tabular data, or the generic reader with appropriate targetType
        // For now, we'll use the stream method and collect to a list.
        try (Stream<Map<String, Object>> stream = reader.stream(source, ReadOptions.builder().build())) {
            return stream.toList();
        } catch (Exception e) {
            throw new ToolkitException("Failed to read as table", e);
        }
    }

    /**
     * Returns an iterator for reading tabular data from a file, allowing lazy loading.
     * @param source The source file to read.
     * @param format The format of the source file (e.g., CSV, EXCEL).
     * @return An iterator over maps, where each map represents a row.
     * @throws ToolkitException if an error occurs during reading.
     */
    public Iterator<Map<String, Object>> readAsTableIterator(File source, FileFormat format) throws ToolkitException {
        logger.debug("Reading file: " + source.getAbsolutePath() + " as table iterator from " + format);
        // This would require a specific iterator implementation from the reader
        throw new ToolkitException("readAsTableIterator operation not yet implemented.");
    }

    /**
     * Streams tabular data from a file, allowing efficient processing of large files.
     * @param source The source file path to stream from.
     * @param format The format of the source file (e.g., CSV, EXCEL).
     * @return A stream of maps, where each map represents a row.
     * @throws ToolkitException if an error occurs during streaming.
     */
    public Stream<Map<String, Object>> streamAsTable(Path source, FileFormat format) throws ToolkitException {
        logger.debug("Streaming file: " + source.toAbsolutePath() + " as table from " + format);
        // This would require a specific stream implementation from the reader
        return reader.stream(source.toFile(), ReadOptions.builder().build());
    }

    /**
     * Writes an object to a target file in a specified format.
     * @param data The object to write.
     * @param target The target file to write to.
     * @param format The format to write the data in.
     * @throws ToolkitException if an error occurs during writing.
     */
    public void write(Object data, File target, FileFormat format) throws ToolkitException {
        logger.debug("Writing data to file: " + target.getAbsolutePath() + " as " + format);
        writer.write(data, target, WriteOptions.builder().build());
    }

    /**
     * Writes an object to a target output stream in a specified format.
     * @param data The object to write.
     * @param targetStream The target output stream to write to.
     * @param format The format to write the data in.
     * @throws ToolkitException if an error occurs during writing.
     */
    public void write(Object data, OutputStream targetStream, FileFormat format) throws ToolkitException {
        logger.debug("Writing data to output stream as " + format);
        writer.write(data, targetStream, WriteOptions.builder().build());
    }

    /**
     * Transforms data from a source file in one format to a target file in another format.
     * @param source The source file.
     * @param sourceFormat The format of the source file.
     * @param target The target file.
     * @param targetFormat The desired format for the target file.
     * @param options Transformation options.
     * @throws ToolkitException if an error occurs during transformation.
     */
    public void transform(File source, FileFormat sourceFormat, File target, FileFormat targetFormat, TransformOptions options) throws ToolkitException {
        logger.debug("Transforming from " + sourceFormat + " to " + targetFormat + " for file: " + source.getAbsolutePath());
        transformer.transform(source, sourceFormat, target, targetFormat, options);
    }

    /**
     * Validates a file against its format rules or a provided schema.
     * @param source The file to validate.
     * @param format The format of the file.
     * @param options Validation options, including schema file if applicable.
     * @return A {@link ValidationResult} indicating whether the file is valid and any errors found.
     * @throws ToolkitException if an error occurs during validation setup or processing.
     */
    public ValidationResult validate(File source, FileFormat format, ValidationOptions options) throws ToolkitException {
        logger.debug("Validating file: " + source.getAbsolutePath() + " as " + format);
        return validator.validate(source, format, options);
    }

    /**
     * Encrypts a source file.
     * @param source The file to encrypt.
     * @param options Encryption options, including key/password.
     * @return The encrypted file.
     * @throws ToolkitException if an error occurs during encryption.
     */
    public File encrypt(File source, EncryptionOptions options) throws ToolkitException {
        logger.debug("Encrypting file: " + source.getAbsolutePath());
        return securityService.encrypt(source, options);
    }

    /**
     * Decrypts a source file.
     * @param source The file to decrypt.
     * @param options Decryption options, including key/password.
     * @return The decrypted file.
     * @throws ToolkitException if an error occurs during decryption.
     */
    public File decrypt(File source, EncryptionOptions options) throws ToolkitException {
        logger.debug("Decrypting file: " + source.getAbsolutePath());
        return securityService.decrypt(source, options);
    }

    /**
     * Encrypts a source file using a password.
     * This is a convenience method that creates {@link EncryptionOptions} with PBKDF2_PASSWORD.
     * @param source The file to encrypt.
     * @param password The password to use for encryption.
     * @return The encrypted file.
     * @throws ToolkitException if an error occurs during encryption.
     */
    public File encrypt(File source, String password) throws ToolkitException {
        logger.debug("Encrypting file with password: " + source.getAbsolutePath());
        EncryptionOptions options = EncryptionOptions.builder()
                .type(EncryptionType.PBKDF2_PASSWORD)
                .password(password.toCharArray())
                .build();
        return encrypt(source, options);
    }

    /**
     * Decrypts a source file using a password.
     * This is a convenience method that creates {@link EncryptionOptions} with PBKDF2_PASSWORD.
     * @param source The file to decrypt.
     * @param password The password to use for decryption.
     * @return The decrypted file.
     * @throws ToolkitException if an error occurs during decryption.
     */
    public File decrypt(File source, String password) throws ToolkitException {
        logger.debug("Decrypting file with password: " + source.getAbsolutePath());
        EncryptionOptions options = EncryptionOptions.builder()
                .type(EncryptionType.PBKDF2_PASSWORD)
                .password(password.toCharArray())
                .build();
        return decrypt(source, options);
    }

    /**
     * Compresses a source file using the specified compression type.
     * @param source The file to compress.
     * @param compressionType The type of compression to apply (e.g., GZIP, ZIP).
     * @return The compressed file.
     * @throws ToolkitException if an error occurs during compression.
     */
    public File compress(File source, CompressionType compressionType) throws ToolkitException {
        logger.debug("Compressing file: " + source.getAbsolutePath() + " with " + compressionType);
        return compressionService.compress(source, compressionType);
    }

    /**
     * Convenience method for compressing with FileFormat enum values.
     * Maps FileFormat compression types to CompressionType.
     * @param source The file to compress.
     * @param format The compression format (e.g., FileFormat.GZIP, FileFormat.ZIP).
     * @return The compressed file.
     * @throws ToolkitException if an error occurs during compression.
     */
    public File compress(File source, FileFormat format) throws ToolkitException {
        logger.debug("Compressing file: " + source.getAbsolutePath() + " with format " + format);
        CompressionType compressionType = mapFileFormatToCompressionType(format);
        return compress(source, compressionType);
    }

    /**
     * Maps FileFormat compression values to CompressionType.
     * @param format The FileFormat to map.
     * @return The corresponding CompressionType.
     * @throws ToolkitException if the format is not a compression format.
     */
    private CompressionType mapFileFormatToCompressionType(FileFormat format) throws ToolkitException {
        return switch (format) {
            case GZIP -> CompressionType.GZIP;
            case ZIP -> CompressionType.ZIP;
            default -> throw new ToolkitException("FileFormat " + format + " is not a compression format");
        };
    }

    /**
     * Decompresses a source file. The method attempts to auto-detect the compression type.
     * @param source The file to decompress.
     * @return The decompressed file (or a directory for ZIP with multiple entries).
     * @throws ToolkitException if an error occurs during decompression.
     */
    public File decompress(File source) throws ToolkitException {
        logger.debug("Decompressing file: " + source.getAbsolutePath());
        return compressionService.decompress(source);
    }

    /**
     * Decompresses a source file using the specified compression type.
     * This is a convenience method for explicit decompression.
     * @param source The file to decompress.
     * @param compressionType The type of compression to expect (e.g., GZIP, ZIP).
     * @return The decompressed file (or a directory for ZIP with multiple entries).
     * @throws ToolkitException if an error occurs during decompression.
     */
    public File decompress(File source, CompressionType compressionType) throws ToolkitException {
        logger.debug("Decompressing file: " + source.getAbsolutePath() + " with explicit type " + compressionType);
        // The default decompress method should handle type detection, but for explicit calls, you might pass it through
        return compressionService.decompress(source);
    }

    /**
     * Convenience method for decompressing with FileFormat enum values.
     * Maps FileFormat compression types to CompressionType.
     * @param source The file to decompress.
     * @param format The compression format (e.g., FileFormat.GZIP, FileFormat.ZIP).
     * @return The decompressed file.
     * @throws ToolkitException if an error occurs during decompression.
     */
    public File decompress(File source, FileFormat format) throws ToolkitException {
        logger.debug("Decompressing file: " + source.getAbsolutePath() + " with format " + format);
        CompressionType compressionType = mapFileFormatToCompressionType(format);
        return decompress(source, compressionType);
    }

    /**
     * Filters tabular data from a file based on a given predicate.
     * @param source The source file containing tabular data.
     * @param format The format of the source file.
     * @param predicate The predicate to apply for filtering rows.
     * @return A list of maps representing the filtered rows.
     * @throws ToolkitException if an error occurs during filtering.
     */
    public List<Map<String, Object>> filter(File source, FileFormat format, Predicate<Map<String, Object>> predicate) throws ToolkitException {
        logger.debug("Filtering file: " + source.getAbsolutePath() + " as " + format);
        // This would typically read the data and then apply the filter
        List<Map<String, Object>> data = readAsTable(source, format);
        return data.stream().filter(predicate).toList();
    }

    /**
     * Performs an asynchronous write operation.
     * @param data The object to write.
     * @param target The target file path to write to.
     * @param format The format to write the data in.
     * @return A CompletableFuture that completes when the write operation is done.
     */
    public CompletableFuture<Void> writeAsync(Object data, Path target, FileFormat format) {
        logger.debug("Asynchronously writing data to file: " + target.toAbsolutePath() + " as " + format);
        return CompletableFuture.runAsync(() -> {
            try {
                write(data, target.toFile(), format);
            } catch (ToolkitException e) {
                throw new RuntimeException(e); // Wrap for CompletableFuture
            }
        });
    }

    /**
     * Performs an asynchronous read operation for tabular data.
     * @param source The source file path to read from.
     * @param format The format of the source file.
     * @return A CompletableFuture that completes with a list of maps representing the tabular data.
     */
    public CompletableFuture<List<Map<String, Object>>> readAsTableAsync(Path source, FileFormat format) {
        logger.debug("Asynchronously reading file: " + source.toAbsolutePath() + " as table from " + format);
        return CompletableFuture.supplyAsync(() -> {
            try {
                return readAsTable(source.toFile(), format);
            } catch (ToolkitException e) {
                throw new RuntimeException(e); // Wrap for CompletableFuture
            }
        });
    }

    /**
     * Releases any resources held by the toolkit.
     */
    public void close() {
        logger.info("Closing UniversalFileToolkit and releasing resources.");
        // Implement resource cleanup here if necessary (e.g., closing internal readers/writers)
    }

    /**
     * Placeholder for search operation as seen in example. This would typically delegate to filter.
     * @param data The data to search within.
     * @param predicate The predicate to apply for searching.
     * @param <T> The type of data.
     * @return A list of filtered data.
     */
    public <T> List<T> search(List<T> data, Predicate<T> predicate) {
        logger.debug("Searching data with predicate.");
        return data.stream().filter(predicate).toList();
    }

    /**
     * Placeholder for validate method with schema file as seen in example.
     * This would typically delegate to the main validate method with appropriate options.
     * @param source The file to validate.
     * @param format The format of the file.
     * @param schemaFile The schema file for validation.
     * @return A {@link ValidationResult} indicating whether the file is valid and any errors found.
     * @throws ToolkitException if an error occurs during validation setup or processing.
     */
    public ValidationResult validate(File source, FileFormat format, File schemaFile) throws ToolkitException {
        logger.debug("Validating file: " + source.getAbsolutePath() + " as " + format + " with schema: " + schemaFile.getAbsolutePath());
        ValidationOptions options = ValidationOptions.builder().schemaFile(schemaFile).build();
        return validate(source, format, options);
    }
}