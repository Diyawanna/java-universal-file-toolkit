# Universal File Toolkit - Testing Guide

## 1. Create Test Files

First, create these test files in your project root directory:

### input.csv
```csv
id,name,status,age
1,John Doe,active,25
2,Jane Smith,inactive,30
3,Bob Johnson,active,35
4,Alice Brown,active,28
```

### schema.json (JSON Schema for validation)
```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "array",
  "items": {
    "type": "object",
    "properties": {
      "key": {
        "type": "string"
      }
    },
    "required": ["key"]
  }
}
```

## 2. Expected Outputs

When you run the DemoApp, you should see output similar to:

```
[INFO] UniversalFileToolkit initialized with config: ToolkitConfig{...}
Error reading CSV: read operation not yet implemented.
Error writing JSON: write operation not yet implemented.
Error validating JSON: validate operation not yet implemented.
Error encrypting/compressing: encrypt operation not yet implemented.
Error decompressing/decrypting: decompress operation not yet implemented.
Error transforming: transform operation not yet implemented.
Active rows: 1
[INFO] Closing UniversalFileToolkit and releasing resources.
```

## 3. What Each Test Does

1. **CSV Reading Test**: Attempts to read `input.csv` into a list of maps
2. **JSON Writing Test**: Attempts to write dummy data to `output.json`
3. **Validation Test**: Attempts to validate JSON against the schema
4. **Encryption/Compression Test**: Attempts to encrypt and compress a file
5. **Decompression/Decryption Test**: Attempts to decrypt and decompress a file
6. **Transformation Test**: Attempts to convert JSON to Excel format
7. **Search Test**: Successfully filters in-memory data (this one works!)

## 4. Running the Test

### Option A: Run from your project
```bash
# Compile and run
./gradlew run

# Or if you have a run task configured:
./gradlew runDemo
```

### Option B: Create a separate test project
Create a new directory and test your published library:

```bash
mkdir test-universal-toolkit
cd test-universal-toolkit
```

Create `build.gradle.kts`:
```kotlin
plugins {
    application
}

repositories {
    mavenCentral()
    mavenLocal() // To use your locally published library
}

dependencies {
    implementation("com.diyawanna:universal-file-toolkit:1.0.1")
}

application {
    mainClass.set("TestApp")
}
```

Create `src/main/java/TestApp.java` and copy your DemoApp code.

## 5. Files Created During Testing

After running, you should see these files created:
- `schema.json` (empty file, created by the demo)
- `dummy.gz` (empty file, created by the demo)

## 6. Understanding the Output

Since this is a framework/skeleton library, most operations will throw `ToolkitException` with messages like:
- "read operation not yet implemented"
- "write operation not yet implemented"
- etc.

The **search operation** should work because it operates on in-memory data and doesn't require file I/O.

## 7. Next Steps for Implementation

To make the library fully functional, you would need to implement:
- `DefaultReader.read()` method
- `DefaultWriter.write()` method  
- `DefaultValidator.validate()` method
- `DefaultSecurityService.encrypt/decrypt()` methods
- `DefaultCompressionService.compress/decompress()` methods
- `DefaultTransformer.transform()` method

Each would use the appropriate dependencies (Jackson for JSON, Apache POI for Excel, etc.) that are already included in your build.gradle.kts.
