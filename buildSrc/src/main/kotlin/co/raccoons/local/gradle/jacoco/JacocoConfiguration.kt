package co.raccoons.local.gradle.jacoco

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testing.jacoco.tasks.JacocoReport

class JacocoConfiguration(private val reportFormats: List<JacocoReportFormat>) : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply("jacoco")

        project.tasks.withType(JacocoReport::class.java) { jacocoReport ->
            jacocoReport.dependsOn("test")

            for (format in this.reportFormats) {
                format.subscribeTo(jacocoReport.reports)
            }
        }
    }

    class Builder {

        private val enabledFormats = mutableListOf<JacocoReportFormat>()

        fun enable(reportFormat: JacocoReportFormat): Builder {
            this.enabledFormats.add(reportFormat)
            return this
        }

        fun build(): JacocoConfiguration {
            return JacocoConfiguration(this.enabledFormats)
        }
    }

}