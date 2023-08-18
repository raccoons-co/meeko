import co.raccoons.local.gradle.*

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
        .addTag(JavadocTag("apiNote","API Note"))
        .addTag(JavadocTag("implSpec","Implementation Specification"))
        .addTag(JavadocTag("implNote","Implementation Note"))
        .build()

BuildConfiguration.of(project)
    .use(Repository.MAVEN_CENTRAL)
    .use(Repository.MAVEN_LOCAL)
    .use(Version.JAVA.of(20))
    .use(testNgImplementation)
    .use(jacocoConfiguration)
    .use(javadocConfiguration)
