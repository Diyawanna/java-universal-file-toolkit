package com.diyawanna.uft.api;

import com.diyawanna.uft.SecurityException;
import com.diyawanna.uft.ToolkitException;
import com.diyawanna.uft.model.EncryptionOptions;
import com.diyawanna.uft.model.HashType;

import java.io.File;

/**
 * Public interface for performing security-related operations like encryption, decryption, and hashing.
 */
public interface SecurityService {

    /**
     * Encrypts a given file based on the provided encryption options.
     * @param source The file to encrypt.
     * @param options Encryption options, including algorithm, key, and password.
     * @return The encrypted file.
     * @throws SecurityException if an error occurs during encryption.
     */
    File encrypt(File source, EncryptionOptions options) throws SecurityException;

    /**
     * Decrypts a given file based on the provided encryption options.
     * @param source The file to decrypt.
     * @param options Decryption options, including algorithm, key, and password.
     * @return The decrypted file.
     * @throws SecurityException if an error occurs during decryption.
     */
    File decrypt(File source, EncryptionOptions options) throws SecurityException;

    /**
     * Computes the hash of a given file using the specified hash type.
     * @param source The file for which to compute the hash.
     * @param type The type of hash to compute (e.g., MD5, SHA-256).
     * @return The computed hash as a byte array.
     * @throws ToolkitException if an error occurs during hashing.
     */
    byte[] hash(File source, HashType type) throws ToolkitException;
}


