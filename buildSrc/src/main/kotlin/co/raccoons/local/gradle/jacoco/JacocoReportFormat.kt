package co.raccoons.local.gradle.jacoco

import org.gradle.testing.jacoco.tasks.JacocoReportsContainer

enum class JacocoReportFormat {

    HTML {
        override fun subscribeTo(jacocoReportContainer: JacocoReportsContainer) {
            jacocoReportContainer.html.required.set(true);
        }
    },

    XML {
        override fun subscribeTo(jacocoReportContainer: JacocoReportsContainer) {
            jacocoReportContainer.xml.required.set(true);
        }
    },

    CSV {
        override fun subscribeTo(jacocoReportContainer: JacocoReportsContainer) {
            jacocoReportContainer.csv.required.set(true);
        }
    };

    abstract fun subscribeTo(jacocoReportContainer: JacocoReportsContainer)
}