package co.raccoons.local.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

class TestNG(private val dependencyList: List<String>) : Plugin<Project> {

    override fun apply(project: Project) {
        for (dependencyNotation in dependencyList) {
            project.dependencies.add("testImplementation", dependencyNotation)
        }

        project.tasks.withType(Test::class.java) {
            it.useTestNG()
        }
    }

    class Builder {

        private val dependencyList = mutableListOf<String>()

        fun addDependency(dependencyNotation: String): Builder {
            this.dependencyList.add(dependencyNotation)
            return this
        }

        fun build(): TestNG {
            return TestNG(this.dependencyList.toList())
        }
    }
}