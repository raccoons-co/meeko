package co.raccoons.local.gradle.checkstyle

import org.gradle.api.plugins.quality.CheckstyleReports

enum class CheckstyleReportFormat {

    HTML {
        override fun subscribeTo(checkstyleReports: CheckstyleReports) {
            checkstyleReports.html.required.set(true)
        }

    },

    XML {
        override fun subscribeTo(checkstyleReports: CheckstyleReports) {
            checkstyleReports.xml.required.set(true)
        }

    };

    abstract fun subscribeTo(checkstyleReports: CheckstyleReports)
}