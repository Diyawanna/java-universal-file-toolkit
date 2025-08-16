# Development Guidelines for Universal File Toolkit

This document outlines the guidelines and best practices for contributing to the `universal-file-toolkit` project.

## 1. Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Gradle (Wrapper is included, so direct installation is not strictly necessary)
- Git

### Cloning the Repository

```bash
git clone https://github.com/Diyawanna/java-universal-file-toolkit.git
cd java-universal-file-toolkit
```

### Building the Project

Use the Gradle wrapper to build the project:

```bash
./gradlew build
```

This command will compile the source code, run tests, and generate the JAR files.

## 2. Code Style and Quality

- **Java Code Style**: Adhere to standard Java conventions. Use an IDE (IntelliJ IDEA, Eclipse) with default formatting settings.
- **Naming Conventions**: Follow standard Java naming conventions for classes, methods, variables, etc.
- **Comments**: Provide clear and concise Javadoc comments for all public classes, methods, and significant fields. Use inline comments for complex logic.
- **Error Handling**: Implement robust error handling using custom `ToolkitException` and its subtypes. Ensure exceptions provide clear messages and proper cause chaining.
- **Logging**: Use SLF4J for all logging. Configure logging levels appropriately.
- **Thread Safety**: Ensure all public APIs are thread-safe, especially for concurrent read/write operations on different files/streams.

## 3. Testing

- **Unit Tests**: All new features and bug fixes must be accompanied by comprehensive unit tests. Aim for 80%+ code coverage.
  - Use JUnit 5 for writing tests.
  - Use Mockito for mocking dependencies.
- **Integration Tests**: For complex functionalities (e.g., large file streaming, encryption round-trip, cross-format transformations), write integration tests.

### Running Tests

```bash
./gradlew test
```

## 4. Dependencies

- **Minimize Dependencies**: Strive to keep the number of external dependencies to a minimum to maintain a lightweight library.
- **Well-Established Libraries**: Prefer well-established, actively maintained, and small libraries.
- **Dependency Management**: All dependencies should be managed via `build.gradle.kts`.

## 5. Documentation

- **Javadoc**: Maintain up-to-date Javadoc for all public API elements.
- **README.md**: Update the `README.md` with any new features, usage examples, or installation instructions.
- **CHANGELOG.md**: Document all significant changes, new features, bug fixes, and breaking changes in `CHANGELOG.md`.

## 6. Git Workflow

- **Branching**: Use a feature-branch workflow. Create a new branch for each feature or bug fix.
  ```bash
  git checkout -b feature/your-feature-name
  ```
- **Commits**: Write clear, concise commit messages. Each commit should represent a single logical change.
- **Pull Requests**: Submit pull requests to the `main` branch. Ensure your PR passes all CI checks (build, tests, linting).

## 7. Release Process

Refer to `PUBLISHMENT.md` for details on how to publish new versions of the library to Maven Central.


