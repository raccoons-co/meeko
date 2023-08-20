package co.raccoons.local.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle build configurator.
 */
class GradleBuild private constructor() {

    companion object {

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
            }
            return BuildConfigurationHandler(project)
        }
    }

    fun interface ConfigurationHandler {
        fun use(plugin: Plugin<Project>): ConfigurationHandler
    }
}
