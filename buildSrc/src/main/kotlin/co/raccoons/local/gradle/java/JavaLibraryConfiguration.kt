package co.raccoons.local.gradle.java

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * The Java Library plugin configuration.
 */
class JavaLibraryConfiguration private constructor(
    private val dependencyConfiguration: DependencyConfiguration
) : Plugin<Project> {

    companion object {
        private const val JAVA_LIBRARY_PLUGIN_ID = "java-library"

        /** Returns default configuration of the Java Library plugin. */
        fun default(): JavaLibraryConfiguration = newBuilder().build()

        /** Returns new plugin configuration builder. */
        fun newBuilder() = Builder()

        /** The configuration builder */
        class Builder {

            private val dependencyConfiguration = DependencyConfiguration()

            fun addDependency(dependency: Dependency): Builder {
                dependencyConfiguration.add(dependency)
                return this
            }

            fun build() = JavaLibraryConfiguration(this.dependencyConfiguration)
        }
    }

    /** @inheritDoc */
    override fun apply(project: Project) {
        project.plugins.apply(JAVA_LIBRARY_PLUGIN_ID)
        dependencyConfiguration.apply(project)
    }
}
