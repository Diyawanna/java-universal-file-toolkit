# Publishing Guidelines for Universal File Toolkit

This document outlines the steps required to publish new versions of the `universal-file-toolkit` library to Maven Central.

## 1. Prerequisites

- **GPG Key**: You need a GPG key pair to sign the artifacts. If you don't have one, generate it:
  ```bash
  gpg --full-generate-key
  ```
  Ensure your public key is uploaded to a public key server (e.g., `hkp://keyserver.ubuntu.com`):
  ```bash
  gpg --keyserver hkp://keyserver.ubuntu.com --send-keys YOUR_KEY_ID
  ```
- **Sonatype OSSRH Account**: You need an account on Sonatype OSSRH (Open Source Software Repository Hosting) and have requested and been granted access to the `com.diyawanna` group ID.
- **Environment Variables**: Set the following environment variables with your Sonatype OSSRH credentials:
  ```bash
  export OSSRH_USERNAME="your-ossrh-username"
  export OSSRH_PASSWORD="your-ossrh-password"
  ```
  And for GPG signing:
  ```bash
  export GPG_KEY_ID="YOUR_KEY_ID" # The ID of your GPG key
  export GPG_PASSPHRASE="your-gpg-passphrase"
  ```
  The `build.gradle.kts` is configured to use a GPG wrapper script that reads the passphrase from an environment variable, which is more secure than hardcoding it.

## 2. Prepare for Release

1. **Update Version**: Increment the `version` in `build.gradle.kts` to the new release version (e.g., `1.0.0`).
2. **Update `CHANGELOG.md`**: Document all changes for the new version in `CHANGELOG.md`.
3. **Clean Build**: Ensure your project is clean and all tests pass:
   ```bash
   ./gradlew clean build
   ```

## 3. Publish to Sonatype OSSRH Staging Repository

To publish the artifacts to the Sonatype OSSRH staging repository, use the `publish` Gradle task:

```bash
./gradlew publish
```

This command will:
- Build the project.
- Generate Javadoc and sources JARs.
- Sign all artifacts using your GPG key.
- Upload the signed artifacts to your staging repository on OSSRH.

## 4. Release from Sonatype OSSRH

After successful publication to the staging repository, you need to manually release the artifacts to Maven Central via the Sonatype OSSRH web interface:

1. **Log in**: Go to [https://s01.oss.sonatype.org/](https://s01.oss.sonatype.org/) and log in with your OSSRH credentials.
2. **Navigate to Staging Repositories**: Click on "Staging Repositories" on the left-hand navigation.
3. **Find Your Repository**: Locate your staging repository (it will typically be named after your `com.diyawanna` group ID with a timestamp).
4. **Close Repository**: Select your repository and click the "Close" button. This action validates all artifacts against Maven Central requirements (e.g., Javadoc, sources, signatures). If there are any errors, you will see them here and need to fix them before proceeding.
5. **Release Repository**: Once the repository is successfully closed, select it again and click the "Release" button. This will promote your artifacts to Maven Central.

## 5. Verify Publication

It may take some time (a few hours) for the artifacts to synchronize with Maven Central. You can verify the publication by searching for your library on [Maven Central](https://search.maven.org/).

## 6. Post-Release Steps

- **Tag Release**: Create a Git tag for the released version:
  ```bash
  git tag -a v1.0.0 -m "Release version 1.0.0"
  git push origin v1.0.0
  ```
- **Update `CHANGELOG.md`**: Add a new entry for the next development iteration (e.g., `1.0.1-SNAPSHOT`).
- **Update `build.gradle.kts`**: Change the `version` in `build.gradle.kts` to the next snapshot version (e.g., `1.0.1-SNAPSHOT`).


