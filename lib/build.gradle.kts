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
import co.raccoons.local.gradle.publish.maven.Pom
import co.raccoons.local.gradle.publish.maven.License
import co.raccoons.local.gradle.publish.maven.Publication
import co.raccoons.local.gradle.repository.Repository
import co.raccoons.local.gradle.test.TestNG

BuildWorkflow.of(project)
    .setGroup("co.raccoons")
    .setVersion("1.0")
    .use(Repository.MAVEN_LOCAL)
    .use(Repository.MAVEN_CENTRAL)
    .use(JavaLibraryConfiguration.default())
    .use(Version.JAVA.of(20))
    .use(Configuration.testNG())
    .use(Configuration.jacoco())
    .use(Configuration.javadoc())
    .use(Configuration.checkstyle())
    .use(Configuration.mavenPublish())


internal object Configuration {

    fun testNG() =
        TestNG.newBuilder()
            .addDependency(TestImplementation("org.testng", "testng", "7.8.0"))
            .addDependency(TestImplementation("org.slf4j", "slf4j-simple", "2.0.7"))
            .build()

    fun jacoco() =
        JacocoConfiguration.newBuilder()
            .enable(JacocoReportFormat.HTML)
            .enable(JacocoReportFormat.XML)
            .build()

    fun javadoc() =
        JavadocConfiguration.newBuilder()
            .addTag(JavadocTag("apiNote", "API Note"))
            .addTag(JavadocTag("implSpec", "Implementation Specification"))
            .addTag(JavadocTag("implNote", "Implementation Note"))
            .build()

    fun checkstyle() =
        CheckstyleConfiguration.newBuilder()
            .setVersion("10.12.2")
            .enable(CheckstyleReportFormat.HTML)
            .build()

    fun mavenPublish(): MavenPublishConfiguration {
        val license =
            License.newBuilder()
                .setName("Meeko")
                .setUrl("https://opensource.org/license/mit")
                .build()

        val pom =
            Pom.newBuilder()
                .setName("Meeko")
                .setDescription("Java Base Util")
                .setUrl("https://bus.raccoons.co/artefacts/meeko")
                .setLicense(license)
                .build()

        val publication =
            Publication.newBuilder()
                .setArtifactId("meeko")
                .setPom(pom)
                .build()

        return MavenPublishConfiguration(publication)
    }
}
