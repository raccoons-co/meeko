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

    companion object {
        fun newBuilder(): TestNgBuilder {
            class Builder : TestNgBuilder {

                private val dependencyScope = DependencyScope()

                override fun addDependency(dependency: Dependency): Builder {
                    this.dependencyScope.add(dependency)
                    return this
                }

                override fun build() = TestNG(this.dependencyScope)
            }
            return Builder()
        }

        interface TestNgBuilder {

            fun addDependency(dependency: Dependency): TestNgBuilder

            fun build(): TestNG
        }

    }

}
