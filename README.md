# Universal File Toolkit

[![Maven Central](https://img.shields.io/maven-central/v/com.diyawanna/universal-file-toolkit.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.diyawanna%22%20AND%20a:%22universal-file-toolkit%22)
[![Java Version](https://img.shields.io/badge/Java-8%2B-blue.svg)](https://openjdk.java.net/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://github.com/Diyawanna/java-universal-file-toolkit/workflows/CI/badge.svg)](https://github.com/Diyawanna/java-universal-file-toolkit/actions)

A comprehensive, lightweight Java library that provides developers with simple, consistent, and secure APIs for reading, writing, transforming, and processing multiple file formats including JSON, CSV, XML, Excel, and YAML.

## 🚀 Features

- **Multi-Format Support**: Read and write JSON, CSV, XML, Excel (XLS/XLSX), and YAML files
- **Format Transformation**: Convert between any supported formats seamlessly
- **Streaming Support**: Memory-efficient processing for large files
- **Security Features**: Built-in encryption/decryption with AES and RSA support
- **Compression**: GZIP and ZIP compression/decompression capabilities
- **Validation**: Schema validation for JSON, XML, and CSV formats
- **Performance Tracking**: Optional performance metrics and logging
- **Thread-Safe**: Concurrent operations support
- **Extensible**: Plugin architecture for custom format handlers
- **Zero Configuration**: Works out-of-the-box with sensible defaults

## 📦 Installation

### Maven

```xml
<dependency>
    <groupId>com.diyawanna</groupId>
    <artifactId>universal-file-toolkit</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.diyawanna:universal-file-toolkit:1.0.0'
```

### Gradle (Kotlin DSL)

```kotlin
implementation("com.diyawanna:universal-file-toolkit:1.0.0")
```

## 🔧 Requirements

- **Java 8+** (tested with Java 8, 11, 17, 21)
- **Maven 3.6+** or **Gradle 6.0+** for building from source

## 🏁 Quick Start

### Basic File Operations

```java
import com.diyawanna.uft.UniversalFileToolkit;
import com.diyawanna.uft.ToolkitConfig;
import com.diyawanna.uft.model.FileFormat;

// Create toolkit instance
ToolkitConfig config = ToolkitConfig.builder()
    .enableLogging(true)
    .enablePerformanceTracking(true)
    .build();

UniversalFileToolkit toolkit = new UniversalFileToolkit(config);

// Read a JSON file
Map<String, Object> data = toolkit.read(
    new File("data.json"), 
    FileFormat.JSON, 
    Map.class
);

// Write data to CSV
toolkit.write(data, new File("output.csv"), FileFormat.CSV);

// Transform JSON to Excel
toolkit.transform(
    new File("input.json"), FileFormat.JSON,
    new File("output.xlsx"), FileFormat.EXCEL,
    TransformOptions.builder().preserveHeaders(true).build()
);
```

### Reading Tabular Data

```java
// Read CSV as table
List<Map<String, Object>> rows = toolkit.readAsTable(
    new File("employees.csv"), 
    FileFormat.CSV
);

// Stream large files efficiently
try (Stream<Map<String, Object>> stream = toolkit.streamAsTable(
    Paths.get("large-dataset.csv"), FileFormat.CSV)) {
    
    stream.filter(row -> (Integer) row.get("age") > 25)
          .forEach(System.out::println);
}
```

### Security Features

```java
// Encrypt a file
EncryptionOptions encryptOptions = EncryptionOptions.builder()
    .type(EncryptionType.AES)
    .password("mySecretPassword".toCharArray())
    .build();

File encryptedFile = toolkit.encrypt(new File("sensitive.json"), encryptOptions);

// Decrypt the file
File decryptedFile = toolkit.decrypt(encryptedFile, encryptOptions);
```

### Compression

```java
// Compress a file
File compressedFile = toolkit.compress(
    new File("large-data.json"), 
    CompressionType.GZIP
);

// Decompress
File decompressedFile = toolkit.decompress(compressedFile);
```

## 📚 Detailed Usage

### Configuration Options

```java
ToolkitConfig config = ToolkitConfig.builder()
    .enableLogging(true)
    .enablePerformanceTracking(true)
    .defaultEncryption(EncryptionType.AES)
    .defaultCharset(StandardCharsets.UTF_8)
    .externalLogger(LoggerFactory.getLogger("MyApp"))
    .build();
```

### Validation

```java
// Validate JSON against schema
ValidationOptions options = ValidationOptions.builder()
    .schemaFile(new File("schema.json"))
    .failFast(false)
    .build();

ValidationResult result = toolkit.validate(
    new File("data.json"), 
    FileFormat.JSON, 
    options
);

if (!result.isValid()) {
    result.getErrors().forEach(error -> 
        System.err.println(error.getPath() + ": " + error.getMessage())
    );
}
```

### Async Operations

```java
// Async file writing
CompletableFuture<Void> writeTask = toolkit.writeAsync(
    data, 
    Paths.get("output.json"), 
    FileFormat.JSON
);

// Async reading
CompletableFuture<List<Map<String, Object>>> readTask = 
    toolkit.readAsTableAsync(Paths.get("data.csv"), FileFormat.CSV);

// Handle completion
readTask.thenAccept(rows -> {
    System.out.println("Read " + rows.size() + " rows");
});
```

### Performance Tracking

```java
OperationResult<List<Map<String, Object>>> result = 
    toolkit.readWithPerformance(file, FileFormat.CSV, List.class);

PerformanceReport report = result.getPerformanceReport();
System.out.println("Operation took: " + report.getElapsedMillis() + "ms");
System.out.println("Bytes read: " + report.getBytesRead());
System.out.println("Peak memory: " + report.getPeakMemoryBytes());
```

## 🔌 Extensibility

### Custom Format Plugin

```java
public class CustomFormatPlugin implements FormatPlugin {
    @Override
    public boolean supports(FileFormat format) {
        return format == FileFormat.CUSTOM;
    }
    
    @Override
    public Reader<?> createReader() {
        return new CustomFormatReader();
    }
    
    @Override
    public Writer createWriter() {
        return new CustomFormatWriter();
    }
}

// Register plugin
ToolkitConfig config = ToolkitConfig.builder()
    .addPlugin(new CustomFormatPlugin())
    .build();
```

## 🎯 Supported Formats

| Format | Read | Write | Streaming | Validation | Notes |
|--------|------|-------|-----------|------------|-------|
| JSON | ✅ | ✅ | ✅ | ✅ (JSON Schema) | Jackson-based |
| CSV | ✅ | ✅ | ✅ | ✅ (Header/Type) | RFC 4180 compliant |
| XML | ✅ | ✅ | ✅ | ✅ (XSD) | DOM/SAX support |
| Excel | ✅ | ✅ | ✅ | ✅ (Basic) | XLS and XLSX |
| YAML | ✅ | ✅ | ✅ | ✅ (Structure) | SnakeYAML-based |

## 🔒 Security Features

- **AES Encryption**: 128, 192, 256-bit keys
- **RSA Encryption**: Public/private key encryption
- **Password-based Encryption**: PBKDF2 with configurable iterations
- **File Hashing**: MD5, SHA-1, SHA-256, SHA-512
- **Secure Key Handling**: Automatic memory cleanup for sensitive data

## ⚡ Performance Considerations

- **Streaming**: Use `streamAsTable()` for files > 100MB
- **Memory Efficient**: Lazy loading for large datasets
- **Thread Safety**: All public APIs are thread-safe
- **Resource Management**: Auto-closeable resources with try-with-resources

## 🧪 Examples

Check out the [examples directory](examples/) for comprehensive usage examples:

- [Basic Operations](examples/BasicOperations.java)
- [Format Transformations](examples/FormatTransformations.java)
- [Security Features](examples/SecurityExamples.java)
- [Streaming Large Files](examples/StreamingExamples.java)
- [Validation Examples](examples/ValidationExamples.java)
- [Async Operations](examples/AsyncExamples.java)

## 🐛 Error Handling

The toolkit uses a comprehensive exception hierarchy:

```java
try {
    toolkit.read(file, FileFormat.JSON, Map.class);
} catch (ValidationException e) {
    // Handle validation errors
} catch (SecurityException e) {
    // Handle encryption/decryption errors
} catch (IOProcessingException e) {
    // Handle I/O errors
} catch (ToolkitException e) {
    // Handle general toolkit errors
}
```

## 🏗️ Building from Source

```bash
# Clone the repository
git clone https://github.com/Diyawanna/java-universal-file-toolkit.git
cd java-universal-file-toolkit

# Build with Gradle
./gradlew build

# Run tests
./gradlew test

# Generate documentation
./gradlew javadoc
```

## 🧪 Testing

```bash
# Run all tests
./gradlew test

# Run integration tests
./gradlew integrationTest

# Generate coverage report
./gradlew jacocoTestReport
```

Coverage reports are available at `build/reports/jacoco/test/html/index.html`

## 📖 API Documentation

Complete API documentation is available at: [JavaDoc](https://diyawanna.github.io/java-universal-file-toolkit/)

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guidelines](CONTRIBUTING.md) for details.

### Development Setup

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Make your changes and add tests
4. Ensure tests pass: `./gradlew test`
5. Commit your changes: `git commit -m 'Add amazing feature'`
6. Push to the branch: `git push origin feature/amazing-feature`
7. Open a Pull Request

### Code Style

- Follow [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- Use meaningful variable and method names
- Add comprehensive JavaDoc comments
- Maintain test coverage above 80%

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

- **Documentation**: [API Docs](https://diyawanna.github.io/java-universal-file-toolkit/)
- **Issues**: [GitHub Issues](https://github.com/Diyawanna/java-universal-file-toolkit/issues)
- **Discussions**: [GitHub Discussions](https://github.com/Diyawanna/java-universal-file-toolkit/discussions)
- **Email**: tech@diyawanna.com

## 🎯 Roadmap

### Version 1.1.0
- [ ] Streaming Excel writing support
- [ ] Additional compression algorithms (BZIP2, LZ4)
- [ ] JSON Schema Draft 2019-09 support
- [ ] Performance optimizations for large files

### Version 1.2.0
- [ ] Parquet format support
- [ ] Database integration (JDBC)
- [ ] Cloud storage providers (S3, Azure Blob)
- [ ] Advanced transformation functions

## 🙏 Acknowledgments

- [Jackson](https://github.com/FasterXML/jackson) for JSON/YAML processing
- [Apache POI](https://poi.apache.org/) for Excel file support
- [Apache Commons CSV](https://commons.apache.org/proper/commons-csv/) for CSV processing
- [BouncyCastle](https://www.bouncycastle.org/) for cryptographic functions

## 📊 Project Stats

- **Language**: Java
- **Build Tool**: Gradle
- **Dependencies**: Minimal, well-established libraries
- **Test Coverage**: 85%+
- **Documentation**: 100% API coverage

---

<div align="center">
  <p>Made with ❤️ by <a href="https://tech.diyawanna.com">Diyawanna</a></p>
  <p>
    <a href="https://github.com/Diyawanna/java-universal-file-toolkit">⭐ Star this project</a> if you find it useful!
  </p>
</div>