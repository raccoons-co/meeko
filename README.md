[![GitHub Actions](https://github.com/raccoons-co/meeko/actions/workflows/EntryPoint.yml/badge.svg?event=push)](https://github.com/raccoons-co/meeko/actions/workflows/EntryPoint.yml)
[![codecov](https://codecov.io/gh/raccoons-co/meeko/graph/badge.svg?token=FtCvNhCrBK)](https://codecov.io/gh/raccoons-co/meeko)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=raccoons-co_meeko&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=raccoons-co_meeko)

Java Base Util
---
Refactored from procedural code into object-oriented:
- `java.util.Optional` 
- `java.util.OptionalInt`

*Always leave the code you are working on a little bit better than you found it.*

build.gradle.kts
---

~~~Kotlin
import co.raccoons.local.gradle.GradleBuild
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
import co.raccoons.local.gradle.fs.clean

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
~~~
