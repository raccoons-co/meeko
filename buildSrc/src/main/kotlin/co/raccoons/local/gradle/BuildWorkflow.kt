package co.raccoons.local.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle build configurator.
 */
object BuildWorkflow {
    /**
     * Returns build configuration handler of this project.
     */
    fun of(project: Project): ConfigurationHandler {
        /**
         * Build configuration handler that applies configuration to project.
         */
        class BuildConfigurationHandler(private val project: Project) : ConfigurationHandler {
            /** inheritDoc */
            override fun use(plugin: Plugin<Project>): ConfigurationHandler {
                plugin.apply(this.project)
                return this
            }

            /** inheritDoc */
            override fun setGroup(group: String): ConfigurationHandler {
                this.project.group = group
                return this
            }

            /** inheritDoc */
            override fun setVersion(version: String): ConfigurationHandler {
                this.project.version = version
                return this
            }
        }
        return BuildConfigurationHandler(project)
    }

    interface ConfigurationHandler {
        /**
         * Puts plugin into operation
         */
        fun use(plugin: Plugin<Project>): ConfigurationHandler

        /**
         * Sets the group of this project.
         *
         * @param group The group of this project.
         */
        fun setGroup(group: String): ConfigurationHandler

        /**
         * Sets the version of this project.
         *
         * @param version The version of this project.
         */
        fun setVersion(version: String): ConfigurationHandler
    }
}
