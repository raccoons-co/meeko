/*
 * Copyright 2023, Raccoons. Developing simple way to change.
 *
 * @license MIT
 */

import co.raccoons.local.gradle.BuildWorkflow
import co.raccoons.local.gradle.checkstyle.CheckstyleConfiguration
import co.raccoons.local.gradle.checkstyle.CheckstyleReportFormat
import co.raccoons.local.gradle.jacoco.JacocoConfiguration
import co.raccoons.local.gradle.jacoco.JacocoReportFormat
import co.raccoons.local.gradle.java.JavaLibraryConfiguration
import co.raccoons.local.gradle.java.TestImplementation
import co.raccoons.local.gradle.javacompile.Version
import co.raccoons.local.gradle.javadoc.JavadocConfiguration
import co.raccoons.local.gradle.javadoc.JavadocTag
import co.raccoons.local.gradle.publish.MavenPublishConfiguration
import co.raccoons.local.gradle.repository.Repository
import co.raccoons.local.gradle.test.TestNG

BuildWorkflow.of(project)
    .setGroup("co.raccoons")
    .setVersion("1.0")
    .use(Repository.MAVEN_LOCAL)
    .use(Repository.MAVEN_CENTRAL)
    .use(Configuration.javaLibrary)
    .use(Version.JAVA.of(20))
    .use(Configuration.testNG)
    .use(Configuration.jacoco)
    .use(Configuration.javadoc)
    .use(Configuration.checkstyle)
    .use(Configuration.mavenPublish)

internal object Configuration {

    val javaLibrary =
        JavaLibraryConfiguration.default()

    val testNG =
        TestNG.Builder()
            .addDependency(TestImplementation("org.testng", "testng", "7.8.0"))
            .addDependency(TestImplementation("org.slf4j", "slf4j-simple", "2.0.7"))
            .build()

    val jacoco =
        JacocoConfiguration.Builder()
            .enable(JacocoReportFormat.HTML)
            .enable(JacocoReportFormat.XML)
            .build()

    val javadoc =
        JavadocConfiguration.Builder()
            .addTag(JavadocTag("apiNote", "API Note"))
            .addTag(JavadocTag("implSpec", "Implementation Specification"))
            .addTag(JavadocTag("implNote", "Implementation Note"))
            .build()

    val checkstyle =
        CheckstyleConfiguration.Builder()
            .setVersion("10.12.2")
            .enable(CheckstyleReportFormat.HTML)
            .build()

    val mavenPublish =
        MavenPublishConfiguration.Builder()
            .build()
}
