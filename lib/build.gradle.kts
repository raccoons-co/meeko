import co.raccoons.local.gradle.BuildConfiguration
import co.raccoons.local.gradle.checkstyle.CheckstyleConfiguration
import co.raccoons.local.gradle.checkstyle.CheckstyleReportFormat
import co.raccoons.local.gradle.jacoco.JacocoConfiguration
import co.raccoons.local.gradle.jacoco.JacocoReportFormat
import co.raccoons.local.gradle.javacompile.Version
import co.raccoons.local.gradle.javadoc.JavadocConfiguration
import co.raccoons.local.gradle.javadoc.JavadocTag
import co.raccoons.local.gradle.repository.Repository
import co.raccoons.local.gradle.test.TestNgImplementation

plugins {
    `java-library`
}

val testNgImplementation =
    TestNgImplementation.Builder()
        .addDependency("org.testng:testng:7.8.0")
        .addDependency("org.slf4j:slf4j-simple:2.0.7")
        .build();

val jacocoConfiguration =
    JacocoConfiguration.Builder()
        .enable(JacocoReportFormat.HTML)
        .enable(JacocoReportFormat.XML)
        .build()

val javadocConfiguration =
    JavadocConfiguration.Builder()
        .addTag(JavadocTag("apiNote", "API Note"))
        .addTag(JavadocTag("implSpec", "Implementation Specification"))
        .addTag(JavadocTag("implNote", "Implementation Note"))
        .build()

val checkstyleConfiguration =
    CheckstyleConfiguration.Builder()
        .setVersion("10.12.2")
        .enable(CheckstyleReportFormat.HTML)
        .build()

BuildConfiguration.of(project)
    .use(Repository.MAVEN_CENTRAL)
    .use(Repository.MAVEN_LOCAL)
    .use(Version.JAVA.of(20))
    .use(testNgImplementation)
    .use(jacocoConfiguration)
    .use(javadocConfiguration)
    .use(checkstyleConfiguration)
