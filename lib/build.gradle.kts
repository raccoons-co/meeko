plugins {
    `java-library`
    jacoco
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.8.0")
    testImplementation("org.slf4j:slf4j-simple:2.0.7")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}

tasks {
    withType<Test> {
        useTestNG()
    }

    withType<Javadoc> {
        (options as StandardJavadocDocletOptions)
            .tags(
                "apiNote:a:API Note:",
                "implSpec:a:Implementation Specification:",
                "implNote:a:Implementation Note:"
            )
    }

    named<JacocoReport>("jacocoTestReport") {
        dependsOn("test")

        reports {
            html.required.set(true)
            xml.required.set(true)
        }
    }
}
