package co.raccoons.local.gradle.java

import org.gradle.api.Plugin
import org.gradle.api.Project

private const val JAVA_LIBRARY_PLUGIN_ID = "java-library"

class JavaLibraryConfiguration private constructor(private val dependencyScope: DependencyScope) :
    Plugin<Project> {

    companion object {
        fun default() = Builder().build()
    }

    override fun apply(project: Project) {
        this.setupPlugin(project)
    }

    private fun setupPlugin(project: Project) {
        project.plugins.apply(JAVA_LIBRARY_PLUGIN_ID)
        dependencyScope.apply(project)
    }

    class Builder {

        private val dependencyScope = DependencyScope()

        fun addDependency(dependency: Dependency): Builder {
            this.dependencyScope.add(dependency)
            return this
        }

        public fun build() = JavaLibraryConfiguration(this.dependencyScope)
    }
}
