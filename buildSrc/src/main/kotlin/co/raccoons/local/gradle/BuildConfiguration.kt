package co.raccoons.local.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion

class BuildConfiguration private constructor() {

    companion object {
        fun of(project: Project): Builder {
            return Builder(project)
        }
    }

    class Builder(private val project: Project) {

        fun useRepository(repository: Repository): Builder {
            repository.applyTo(this.project)
            return this
        }

        fun setJavaVersion(version: JavaLanguageVersion): Builder {
            JavaVersionSetter(version).apply(this.project)
            return this
        }

        fun setTestImplementation(testImplementation: Plugin<Project>): Builder {
            testImplementation.apply(this.project)
            return this
        }

        fun setJacoco(): Builder {
            Jacoco().apply(this.project)
            return this
        }

/*

        fun setupJaCoCo(): Builder {
            this.project.apply(plugin = "jacoco")

            this.project.tasks.withType<JacocoReport> {
                dependsOn("test")
                reports {
                    html.required.set(true)
                    xml.required.set(true)
                }
            }
            return this
        }

        fun setupJavadoc() {
            this.project.tasks.withType<Javadoc> {
                (options as StandardJavadocDocletOptions)
                    .tags(
                        "apiNote:a:API Note:",
                        "implSpec:a:Implementation Specification:",
                        "implNote:a:Implementation Note:"
                    )
            }
        }

 */
    }
}
