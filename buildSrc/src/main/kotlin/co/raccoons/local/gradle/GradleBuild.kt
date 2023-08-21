package co.raccoons.local.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle build configurator.
 */
object GradleBuild {
    /**
     * Returns build configuration handler of this project.
     */
    fun of(project: Project): ConfigurationHandler {
        /**
         * Build configuration handler that applies configuration to project.
         */
        class BuildConfigurationHandler(private val project: Project) : ConfigurationHandler {
            override fun use(plugin: Plugin<Project>): ConfigurationHandler {
                plugin.apply(this.project)
                return this
            }

            override fun setGroup(group: String): ConfigurationHandler {
                this.project.group = group
                return this
            }

            override fun setVersion(version: String): ConfigurationHandler {
                this.project.version = version
                return this
            }
        }
        return BuildConfigurationHandler(project)
    }

    interface ConfigurationHandler {

        fun use(plugin: Plugin<Project>): ConfigurationHandler

        fun setGroup(group: String): ConfigurationHandler

        fun setVersion(version: String): ConfigurationHandler
    }
}
