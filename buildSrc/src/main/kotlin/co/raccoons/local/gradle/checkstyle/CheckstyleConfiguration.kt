package co.raccoons.local.gradle.checkstyle

import co.raccoons.local.gradle.Presets
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.quality.Checkstyle
import org.gradle.api.plugins.quality.CheckstyleExtension

private const val CHECKSTYLE_PLUGIN_ID = "checkstyle"

class CheckstyleConfiguration(
    private val toolVersion: String,
    private val reportFormats: List<CheckstyleReportFormat>
) : Plugin<Project> {

    override fun apply(project: Project) {
        this.setupPlugin(project)
        this.enableReports(project)
    }

    private fun setupPlugin(project: Project) {
        project.plugins.apply(CHECKSTYLE_PLUGIN_ID)
        project.extensions
            .getByType(CheckstyleExtension::class.java)
            .toolVersion = this.toolVersion
    }

    private fun enableReports(project: Project) {
        project.tasks
            .withType(Checkstyle::class.java)
            .configureEach { checkstyle ->
                for (format in this.reportFormats) {
                    format.subscribeTo(checkstyle.reports)
                }
            }
    }

    class Builder {

        private var toolVersion = Presets.CHECKSTYLE.version()
        private val enabledFormats = mutableListOf<CheckstyleReportFormat>()

        fun setVersion(version: String): Builder {
            this.toolVersion = version
            return this
        }

        fun enable(reportFormat: CheckstyleReportFormat): Builder {
            this.enabledFormats.add(reportFormat)
            return this
        }

        fun build() = CheckstyleConfiguration(this.toolVersion, this.enabledFormats)
    }
}
