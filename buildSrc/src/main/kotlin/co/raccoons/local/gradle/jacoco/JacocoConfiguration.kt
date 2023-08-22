package co.raccoons.local.gradle.jacoco

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testing.jacoco.tasks.JacocoReport

private const val JACOCO_PLUGIN_ID = "jacoco"

class JacocoConfiguration private constructor(private val reportFormats: List<JacocoReportFormat>) :
    Plugin<Project> {

    override fun apply(project: Project) {
        this.setupPlugin(project)
        this.enableReports(project)
    }

    private fun setupPlugin(project: Project) {
        project.plugins.apply(JACOCO_PLUGIN_ID)
    }

    private fun enableReports(project: Project) {
        project.tasks
            .withType(JacocoReport::class.java)
            .configureEach { jacocoReport ->
                jacocoReport.dependsOn("test")

                for (format in this.reportFormats) {
                    format.subscribeTo(jacocoReport.reports)
                }
            }
    }

    companion object {
        /**
         * Returns new JacocoConfigurationBuilder instance.
         */
        fun newBuilder(): JacocoConfigurationBuilder {
            class Builder : JacocoConfigurationBuilder {

                private val enabledFormats = mutableListOf<JacocoReportFormat>()

                override fun enable(reportFormat: JacocoReportFormat): Builder {
                    this.enabledFormats.add(reportFormat)
                    return this
                }

                override fun build() = JacocoConfiguration(this.enabledFormats)
            }
            return Builder()
        }

        interface JacocoConfigurationBuilder {

            fun enable(reportFormat: JacocoReportFormat): JacocoConfigurationBuilder

            fun build(): JacocoConfiguration
        }
    }
}
