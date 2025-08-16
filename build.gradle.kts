plugins {
    // Apply the java-library plugin to add support for Java Library
    id("java-library")
    // Apply the maven-publish plugin to publish artifacts to Maven Central
    id("maven-publish")
    // Apply the signing plugin for signing artifacts
    id("signing")
//    signing
//    application   // Add this line
}

group = "com.diyawanna"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withJavadocJar()
    withSourcesJar()
}

repositories {
    // Use Maven Central for dependencies
    mavenCentral()
}

dependencies {
    // Jackson for JSON/YAML processing
    api("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.0")
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.17.0")

    // Apache Commons CSV for CSV processing
    api("org.apache.commons:commons-csv:1.10.0")

    // Apache POI for Excel processing
    api("org.apache.poi:poi:5.2.5")
    api("org.apache.poi:poi-ooxml:5.2.5")

    // SLF4J for logging facade
    api("org.slf4j:slf4j-api:2.0.13")

    // BouncyCastle for encryption (optional, can be shaded or provided by user)
    implementation("org.bouncycastle:bcprov-jdk18on:1.78")
    implementation("org.bouncycastle:bcpkix-jdk18on:1.78")

    // Apache Commons Compress for GZIP/ZIP
    api("org.apache.commons:commons-compress:1.26.1")

    // For JSON Schema validation
    api("com.networknt:json-schema-validator:1.3.3")

    // JUnit Jupiter for testing (uncommented for potential future use)
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.0")

    // Mockito for mocking in tests (uncommented for potential future use)
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.11.0")
}

tasks.test {
    // Use JUnit Platform for running tests
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "universal-file-toolkit"
            from(components["java"])

            pom {
                name.set("Universal File Toolkit")
                description.set("A comprehensive Java library for file operations, format conversions, security, and more.")
                url.set("https://github.com/Diyawanna/java-universal-file-toolkit")

                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("wsmr")
                        name.set("Diyawanna")
                        email.set("tech@diyawanna.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/Diyawanna/java-universal-file-toolkit.git")
                    developerConnection.set("scm:git:ssh://github.com:Diyawanna/java-universal-file-toolkit.git")
                    url.set("https://github.com/Diyawanna/java-universal-file-toolkit")
                }
            }
        }
    }

    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = project.findProperty("ossrhUsername") as String?
                password = project.findProperty("ossrhPassword") as String?
            }
        }
    }
}

signing {
    // Use your existing GPG configuration from gradle.properties
    sign(publishing.publications["maven"])
}

// Uncomment if you want to run the application directly
// Add this after your existing configuration
//application {
//    mainClass.set("com.diyawanna.uft.examples.DemoApp")
//}


//// Add a custom task to run the demo
//tasks.register("runDemo") {
//    group = "application"
//    description = "Run the Universal File Toolkit demo"
//    dependsOn("classes")
//    doLast {
//        javaexec {
//            classpath = sourceSets.main.get().runtimeClasspath
//            mainClass.set("com.diyawanna.uft.examples.DemoApp")
//        }
//    }
//}


// Configure jar task
tasks.jar {
    archiveBaseName.set("universal-file-toolkit")
    manifest {
        attributes(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Implementation-Vendor" to "Diyawanna"
        )
    }
}

// Configure sources jar
tasks.named<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
}

// Configure javadoc jar
tasks.named<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
}

// Configure javadoc task
tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

// Ensure that signing tasks depend on the correct publications
tasks.withType<Sign>().configureEach {
    onlyIf { gradle.taskGraph.hasTask("publish") || gradle.taskGraph.hasTask("publishToMavenLocal") }
}