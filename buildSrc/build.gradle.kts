plugins {
    kotlin("jvm") version "1.9.0"
}

repositories{
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(gradleApi())
}

kotlin{
    jvmToolchain(JavaLanguageVersion.of(20).asInt())
}
