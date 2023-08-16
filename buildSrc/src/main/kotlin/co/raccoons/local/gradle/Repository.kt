package co.raccoons.local.gradle

import org.gradle.api.Project

enum class Repository {
    MAVEN_CENTRAL {
        override fun applyTo(project: Project) {
            project.repositories.mavenCentral()
        }
    },
    MAVEN_LOCAL {
        override fun applyTo(project: Project) {
            project.repositories.mavenLocal()
        }
    };

    abstract fun applyTo(project: Project)
}