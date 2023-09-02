package co.raccoons.local.gradle.test

import co.raccoons.local.gradle.java.Dependency
import co.raccoons.local.gradle.java.DependencyConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

class TestNG private constructor(
    private val dependencyConfiguration: DependencyConfiguration
) : Plugin<Project> {

    companion object {

        /** Returns new plugin configuration builder. */
        fun newBuilder() = Builder()

        /** The configuration builder */
        class Builder {

            private val dependencyConfiguration = DependencyConfiguration()

            fun addDependency(dependency: Dependency): Builder {
                this.dependencyConfiguration.add(dependency)
                return this
            }

            fun build() = TestNG(this.dependencyConfiguration)
        }
    }

    /** @inheritDoc */
    override fun apply(project: Project) {
        this.setupPlugin(project)
    }

    private fun setupPlugin(project: Project) {
        dependencyConfiguration.apply(project)

        project.tasks
            .withType(Test::class.java)
            .configureEach { test ->
                test.useTestNG()
            }
    }
}
