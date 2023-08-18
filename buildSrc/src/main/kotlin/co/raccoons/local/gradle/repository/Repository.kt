package co.raccoons.local.gradle.repository

import org.gradle.api.Plugin
import org.gradle.api.Project

enum class Repository: Plugin<Project> {
    MAVEN_CENTRAL {
        override fun apply(project: Project) {
            project.repositories.mavenCentral()
        }
    },
    MAVEN_LOCAL {
        override fun apply(project: Project) {
            project.repositories.mavenLocal()
        }
    }
}