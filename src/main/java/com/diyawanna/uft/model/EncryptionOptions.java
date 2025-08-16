package com.diyawanna.uft.model;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Optional;

/**
 * Options for controlling encryption and decryption operations.
 * Includes settings for encryption type, keys, passwords, and algorithm details.
 */
public final class EncryptionOptions {
    private final EncryptionType type;
    private final Optional<KeyPair> rsaKeyPair;
    private final Optional<PublicKey> rsaPublicKey;
    private final Optional<PrivateKey> rsaPrivateKey;
    private final Optional<char[]> password;
    private final Optional<String> cipherTransformation;
    private final boolean useSalt;
    private final int iterations;

    private EncryptionOptions(Builder builder) {
        this.type = builder.type;
        this.rsaKeyPair = Optional.ofNullable(builder.rsaKeyPair);
        this.rsaPublicKey = Optional.ofNullable(builder.rsaPublicKey);
        this.rsaPrivateKey = Optional.ofNullable(builder.rsaPrivateKey);
        this.password = Optional.ofNullable(builder.password);
        this.cipherTransformation = Optional.ofNullable(builder.cipherTransformation);
        this.useSalt = builder.useSalt;
        this.iterations = builder.iterations;
    }

    /**
     * Returns a new builder for creating {@link EncryptionOptions} instances.
     * @return A new {@link Builder} instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    public EncryptionType getType() {
        return type;
    }

    public Optional<KeyPair> getRsaKeyPair() {
        return rsaKeyPair;
    }

    public Optional<PublicKey> getRsaPublicKey() {
        return rsaPublicKey;
    }

    public Optional<PrivateKey> getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public Optional<char[]> getPassword() {
        return password;
    }

    public Optional<String> getCipherTransformation() {
        return cipherTransformation;
    }

    public boolean isUseSalt() {
        return useSalt;
    }

    public int getIterations() {
        return iterations;
    }

    /**
     * Builder class for {@link EncryptionOptions}.
     */
    public static final class Builder {
        private EncryptionType type = EncryptionType.AES;
        private KeyPair rsaKeyPair = null;
        private PublicKey rsaPublicKey = null;
        private PrivateKey rsaPrivateKey = null;
        private char[] password = null;
        private String cipherTransformation = null;
        private boolean useSalt = true;
        private int iterations = 10000; // Default PBKDF2 iterations

        private Builder() {}

        public Builder type(EncryptionType type) {
            this.type = type;
            return this;
        }

        public Builder rsaKeyPair(KeyPair rsaKeyPair) {
            this.rsaKeyPair = rsaKeyPair;
            return this;
        }

        public Builder rsaPublicKey(PublicKey rsaPublicKey) {
            this.rsaPublicKey = rsaPublicKey;
            return this;
        }

        public Builder rsaPrivateKey(PrivateKey rsaPrivateKey) {
            this.rsaPrivateKey = rsaPrivateKey;
            return this;
        }

        public Builder password(char[] password) {
            this.password = password;
            return this;
        }

        public Builder cipherTransformation(String cipherTransformation) {
            this.cipherTransformation = cipherTransformation;
            return this;
        }

        public Builder useSalt(boolean useSalt) {
            this.useSalt = useSalt;
            return this;
        }

        public Builder iterations(int iterations) {
            this.iterations = iterations;
            return this;
        }

        public EncryptionOptions build() {
            return new EncryptionOptions(this);
        }
    }
}


