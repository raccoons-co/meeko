package co.raccoons.local.gradle.java

import org.gradle.api.Plugin
import org.gradle.api.Project

private const val JAVA_LIBRARY_PLUGIN_ID = "java-library"

class JavaLibraryConfiguration private constructor(private val dependencyScope: DependencyScope) :
    Plugin<Project> {

    override fun apply(project: Project) {
        this.setupPlugin(project)
    }

    private fun setupPlugin(project: Project) {
        project.plugins.apply(JAVA_LIBRARY_PLUGIN_ID)
        dependencyScope.apply(project)
    }

    companion object {

        fun default() = this.newBuilder().build()

        fun newBuilder() = Builder()

        class Builder {

            private val dependencyScope = DependencyScope()

            fun addDependency(dependency: Dependency): Builder {
                this.dependencyScope.add(dependency)
                return this
            }

            fun build() = JavaLibraryConfiguration(this.dependencyScope)
        }
    }
}
