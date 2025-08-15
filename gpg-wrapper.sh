#!/bin/bash
unset GPG_BATCH
export GPG_TTY=$(tty)
# Create a temporary passphrase file
PASSPHRASE_FILE=$(mktemp)
echo "your-gpg-passphrase" > "$PASSPHRASE_FILE"
# Use the passphrase file instead of stdin
gpg --no-batch --passphrase-file "$PASSPHRASE_FILE" --pinentry-mode loopback "$@"
# Clean up the temporary file
rm -f "$PASSPHRASE_FILE"