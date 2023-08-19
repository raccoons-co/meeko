package co.raccoons.local.gradle.java

import org.gradle.api.Plugin
import org.gradle.api.Project

class JavaLibraryConfiguration(private val dependencyScope: DependencyScope) :
    Plugin<Project> {

    companion object {
        public fun default(): JavaLibraryConfiguration {
            return JavaLibraryConfiguration.Builder().build()
        }
    }

    override fun apply(project: Project) {
        this.setupPlugin(project)
    }

    private fun setupPlugin(project: Project) {
        project.plugins.apply("java-library")
        dependencyScope.apply(project)
    }

    class Builder {

        private val dependencyScope = DependencyScope()

        fun addDependency(dependency: Dependency): Builder {
            this.dependencyScope.add(dependency)
            return this
        }

        public fun build(): JavaLibraryConfiguration {
            return JavaLibraryConfiguration(this.dependencyScope)
        }
    }
}