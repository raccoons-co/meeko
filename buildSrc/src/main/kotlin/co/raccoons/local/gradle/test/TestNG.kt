package co.raccoons.local.gradle.test

import co.raccoons.local.gradle.java.Dependency
import co.raccoons.local.gradle.java.DependencyScope
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

class TestNG private constructor(private val dependencyScope: DependencyScope) : Plugin<Project> {

    override fun apply(project: Project) {
        this.setupPlugin(project)
    }

    private fun setupPlugin(project: Project) {
        dependencyScope.apply(project)

        project.tasks
            .withType(Test::class.java)
            .configureEach { test ->
                test.useTestNG()
            }
    }

    class Builder {

        private val dependencyScope = DependencyScope()

        fun addDependency(dependency: Dependency): Builder {
            this.dependencyScope.add(dependency)
            return this
        }

        fun build() = TestNG(this.dependencyScope)
    }
}
