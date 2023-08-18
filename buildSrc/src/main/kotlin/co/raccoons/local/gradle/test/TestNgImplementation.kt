package co.raccoons.local.gradle.test

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

class TestNgImplementation(private val dependencies: List<String>) : Plugin<Project> {

    override fun apply(project: Project) {
        this.setupPlugin(project)
    }

    private fun setupPlugin(project: Project) {
        for (dependencyNotation in dependencies) {
            project.dependencies.add("testImplementation", dependencyNotation)
        }

        project.tasks
            .withType(Test::class.java)
            .configureEach { test ->
                test.useTestNG()
            }
    }

    class Builder {

        private val dependencies = mutableListOf<String>()

        fun addDependency(dependencyNotation: String): Builder {
            this.dependencies.add(dependencyNotation)
            return this
        }

        fun build(): TestNgImplementation {
            return TestNgImplementation(this.dependencies.toList())
        }
    }
}