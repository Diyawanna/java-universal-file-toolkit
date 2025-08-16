package com.diyawanna.uft.examples;

import com.diyawanna.uft.UniversalFileToolkit;
import com.diyawanna.uft.ToolkitConfig;
import com.diyawanna.uft.model.FileFormat;
import com.diyawanna.uft.model.EncryptionType;
import com.diyawanna.uft.model.ValidationResult;
import com.diyawanna.uft.ToolkitException;

import java.io.File;
import java.util.List;
import java.util.Map;

public class DemoApp {
    public static void main(String[] args) {
        try {
            // 1️⃣ Configure toolkit (logging ON, performance tracking ON)
            ToolkitConfig config = ToolkitConfig.builder()
                    .enableLogging(true)
                    .enablePerformanceTracking(true)
                    .defaultEncryption(EncryptionType.AES)
                    .build();

            UniversalFileToolkit uft = new UniversalFileToolkit(config);

            // --- Placeholder for actual file operations ---
            // Due to the nature of this being a project setup, actual file operations
            // are not implemented in the core library yet. These calls will throw
            // ToolkitException as per the current implementation of UniversalFileToolkit.
            // In a fully implemented library, these would perform real operations.

            // 2️⃣ Read a CSV file into a list of maps
            // This will throw ToolkitException as read is not implemented
            try {
                List<Map<String, Object>> csvData = uft.read(new File("input.csv"), FileFormat.CSV, List.class);
                System.out.println("Loaded " + csvData.size() + " rows from CSV.");
            } catch (ToolkitException e) {
                System.err.println("Error reading CSV: " + e.getMessage());
            }

            // 3️⃣ Convert CSV data to JSON and write to file
            // This will throw ToolkitException as write is not implemented
            try {
                uft.write(List.of(Map.of("key", "value")), new File("output.json"), FileFormat.JSON);
                System.out.println("Wrote dummy data to output.json.");
            } catch (ToolkitException e) {
                System.err.println("Error writing JSON: " + e.getMessage());
            }

            // 4️⃣ Validate a JSON file against a schema
            // This will throw ToolkitException as validate is not implemented
            try {
                // Create a dummy schema file for demonstration purposes
                File dummySchemaFile = new File("schema.json");
                if (!dummySchemaFile.exists()) {
                    dummySchemaFile.createNewFile();
                }
                ValidationResult result = uft.validate(new File("output.json"), FileFormat.JSON, dummySchemaFile);
                if (result.isValid()) {
                    System.out.println("JSON is valid ✅");
                } else {
                    System.out.println("JSON validation failed ❌: " + result.getErrors());
                }
            } catch (ToolkitException e) {
                System.err.println("Error validating JSON: " + e.getMessage());
            }

            // 5️⃣ Encrypt and compress a file
            // These will throw ToolkitException as encrypt/compress are not implemented
            try {
                File encrypted = uft.encrypt(new File("output.json"), "secret-password");
                File compressed = uft.compress(encrypted, FileFormat.GZIP);
                System.out.println("Encrypted & compressed file: " + compressed.getAbsolutePath());
            } catch (ToolkitException e) {
                System.err.println("Error encrypting/compressing: " + e.getMessage());
            }

            // 6️⃣ Decompress and decrypt
            // These will throw ToolkitException as decompress/decrypt are not implemented
            try {
                File dummyCompressed = new File("dummy.gz"); // Placeholder
                if (!dummyCompressed.exists()) {
                    dummyCompressed.createNewFile();
                }
                File decompressed = uft.decompress(dummyCompressed, FileFormat.GZIP);
                File decrypted = uft.decrypt(decompressed, "secret-password");
                System.out.println("Restored file: " + decrypted.getAbsolutePath());
            } catch (ToolkitException e) {
                System.err.println("Error decompressing/decrypting: " + e.getMessage());
            }

            // 7️⃣ Transform JSON to Excel
            // This will throw ToolkitException as transform is not implemented
            try {
                uft.transform(new File("output.json"), FileFormat.JSON, new File("output.xlsx"), FileFormat.EXCEL, null);
                System.out.println("Transformed dummy JSON to Excel.");
            } catch (ToolkitException e) {
                System.err.println("Error transforming: " + e.getMessage());
            }

            // 8️⃣ Search in CSV
            // This will throw ToolkitException as readAsTable is not implemented
            try {
                List<Map<String, Object>> dummyCsvData = List.of(Map.of("status", "active"), Map.of("status", "inactive"));
                List<Map<String, Object>> filtered = uft.search(dummyCsvData, row -> row.get("status").equals("active"));
                System.out.println("Active rows: " + filtered.size());
            } catch (Exception e) { // Catch generic Exception as search calls unimplemented methods
                System.err.println("Error searching CSV: " + e.getMessage());
            }

            uft.close();

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}