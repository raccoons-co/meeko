import co.raccoons.local.gradle.BuildConfiguration
import co.raccoons.local.gradle.Repository
import co.raccoons.local.gradle.TestNG

plugins {
    `java-library`
}

val testImplementation =
    TestNG.Builder()
        .addDependency("org.testng:testng:7.8.0")
        .addDependency("org.slf4j:slf4j-simple:2.0.7")
        .build();

BuildConfiguration.of(project)
    .useRepository(Repository.MAVEN_CENTRAL)
    .setJavaVersion(JavaLanguageVersion.of(20))
    .setTestImplementation(testImplementation)
    .setJacoco()


