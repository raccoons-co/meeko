package co.raccoons.local.gradle.jacoco

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
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
            .withType(JacocoReport::class.java) { jacocoReport ->
                jacocoReport.dependsOn("test")

                for (format in this.reportFormats) {
                    format.subscribeTo(jacocoReport.reports)
                }
            }

        project.tasks
            .withType(Test::class.java) { test ->
                test.finalizedBy(project.tasks.withType(JacocoReport::class.java))
            }
    }

    companion object {
        /**
         * Returns new JacocoConfigurationBuilder instance.
         */
        fun newBuilder() = Builder()

        class Builder {

            private val enabledFormats = mutableListOf<JacocoReportFormat>()

            fun enable(reportFormat: JacocoReportFormat): Builder {
                this.enabledFormats.add(reportFormat)
                return this
            }

            fun build() = JacocoConfiguration(this.enabledFormats)
        }
    }
}
