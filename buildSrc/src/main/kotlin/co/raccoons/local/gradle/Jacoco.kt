package co.raccoons.local.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testing.jacoco.tasks.JacocoReport

class Jacoco : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply("jacoco")


        project.tasks.withType(JacocoReport::class.java) {
            it.dependsOn("test")
            it.reports {
                it.html.required.set(true)
                it.xml.required.set(true)
            }
        }
    }
}