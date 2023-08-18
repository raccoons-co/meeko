package co.raccoons.local.gradle.javadoc

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.external.javadoc.StandardJavadocDocletOptions

class JavadocConfiguration(private val tags: List<String>) : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.withType(Javadoc::class.java) { javadoc ->
            (javadoc.options as StandardJavadocDocletOptions).tags(tags)
        }
    }

    class Builder {

        private val tags = mutableListOf<String>()

        fun addTag(tag: JavadocTag): Builder {
            this.tags.add(tag.toString())
            return this
        }

        fun build(): JavadocConfiguration {
            return JavadocConfiguration(this.tags.toList())
        }
    }
}
