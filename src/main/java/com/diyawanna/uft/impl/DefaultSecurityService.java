package com.diyawanna.uft.impl;

import com.diyawanna.uft.SecurityException;
import com.diyawanna.uft.ToolkitException;
import com.diyawanna.uft.api.SecurityService;
import com.diyawanna.uft.model.EncryptionOptions;
import com.diyawanna.uft.model.HashType;

import java.io.File;

/**
 * Placeholder default implementation for the {@link SecurityService} interface.
 * All methods throw {@link ToolkitException} as actual implementation is pending.
 */
public class DefaultSecurityService implements SecurityService {

    @Override
    public File encrypt(File source, EncryptionOptions options) throws SecurityException {
        throw new SecurityException("DefaultSecurityService.encrypt(File, EncryptionOptions) not yet implemented.");
    }

    @Override
    public File decrypt(File source, EncryptionOptions options) throws SecurityException {
        throw new SecurityException("DefaultSecurityService.decrypt(File, EncryptionOptions) not yet implemented.");
    }

    @Override
    public byte[] hash(File source, HashType type) throws ToolkitException {
        throw new ToolkitException("DefaultSecurityService.hash(File, HashType) not yet implemented.");
    }
}


