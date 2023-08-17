[![GitHub Actions](https://github.com/raccoons-co/meeko/actions/workflows/EntryPoint.yml/badge.svg?event=push)](https://github.com/raccoons-co/meeko/actions/workflows/EntryPoint.yml)
[![codecov](https://codecov.io/gh/raccoons-co/meeko/branch/main/graph/badge.svg?token=FtCvNhCrBK)](https://codecov.io/gh/raccoons-co/meeko)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=raccoons-co_meeko&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=raccoons-co_meeko)

Java Base Util
---
Refactored from procedural code into object-oriented:
- `java.util.Optional` 
- `java.util.OptionalInt`

*Always leave the code you are working on a little bit better than you found it.*

Gradle Build Script
---
~~~Kotlin
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
~~~