package co.raccoons.local.gradle.javadoc

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.external.javadoc.StandardJavadocDocletOptions

class JavadocConfiguration private constructor(private val tags: List<String>) : Plugin<Project> {

    override fun apply(project: Project) {
        this.setupPlugin(project)
    }

    private fun setupPlugin(project: Project) {
        project.tasks
            .withType(Javadoc::class.java)
            .configureEach { javadoc ->
                (javadoc.options as StandardJavadocDocletOptions).tags(tags)
            }
    }

    companion object {

        fun newBuilder(): JavadocConfigurationBuilder {


            class Builder : JavadocConfigurationBuilder {

                private val tags = mutableListOf<String>()

                override fun addTag(tag: JavadocTag): Builder {
                    this.tags.add(tag.toString())
                    return this
                }

                override fun build() = JavadocConfiguration(this.tags.toList())
            }

            return Builder()
        }

        interface JavadocConfigurationBuilder {

            fun addTag(tag: JavadocTag): JavadocConfigurationBuilder

            fun build(): JavadocConfiguration
        }
    }
}
