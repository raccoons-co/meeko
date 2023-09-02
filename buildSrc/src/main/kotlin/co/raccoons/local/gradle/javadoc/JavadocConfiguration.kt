package co.raccoons.local.gradle.javadoc

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.external.javadoc.StandardJavadocDocletOptions

/**
 * Javadoc plugin configuration.
 */
class JavadocConfiguration private constructor(private val tags: List<String>) : Plugin<Project> {

    companion object {

        /** Returns new plugin configuration builder. */
        fun newBuilder() = Builder()

        /** The configuration builder */
        class Builder {

            private val tags = mutableListOf<String>()

            fun addTag(tag: JavadocTag): Builder {
                tags.add(tag.toString())
                return this
            }

            fun build() = JavadocConfiguration(tags.toList())
        }
    }

    /** @inheritDoc */
    override fun apply(project: Project) {
        project.tasks
            .withType(Javadoc::class.java)
            .configureEach { javadoc ->
                (javadoc.options as StandardJavadocDocletOptions).tags(tags)
            }
    }
}
