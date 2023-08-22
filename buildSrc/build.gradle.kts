plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("com.google.protobuf") version "0.9.4"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(gradleApi())
    implementation("com.google.protobuf:protobuf-kotlin:3.24.1")
}

kotlin {
    jvmToolchain(JavaLanguageVersion.of(20).asInt())
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.1"
    }
}
