[![GitHub Actions](https://github.com/raccoons-co/meeko/actions/workflows/EntryPoint.yml/badge.svg?event=push)](https://github.com/raccoons-co/meeko/actions/workflows/EntryPoint.yml)
[![codecov](https://codecov.io/gh/raccoons-co/meeko/branch/main/graph/badge.svg?token=FtCvNhCrBK)](https://codecov.io/gh/raccoons-co/meeko)
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
~~~
