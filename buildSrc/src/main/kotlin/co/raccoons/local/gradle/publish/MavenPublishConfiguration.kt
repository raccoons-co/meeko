/*
 * Copyright 2023, Raccoons. Developing simple way to change.
 *
 * @license MIT
 */

package co.raccoons.local.gradle.publish

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication

private const val MAVEN_PUBLISH_PLUGIN_ID = "maven-publish"

class MavenPublishConfiguration private constructor() : Plugin<Project> {

    override fun apply(project: Project) {
        this.setupPlugin(project)
        this.addTaskPackageJavaSources(project)
        this.addTaskPackageJavadoc(project)
        this.createMavenPublication(project)
    }

    private fun setupPlugin(project: Project) {
        project.plugins.apply(MAVEN_PUBLISH_PLUGIN_ID)
    }

    private fun addTaskPackageJavadoc(project: Project) {
        project.extensions
            .getByType(JavaPluginExtension::class.java)
            .withJavadocJar()
    }

    private fun addTaskPackageJavaSources(project: Project) {
        project.extensions
            .getByType(JavaPluginExtension::class.java)
            .withSourcesJar()
    }

    private fun createMavenPublication(project: Project) {
        project.extensions
            .getByType(PublishingExtension::class.java)
            .publications { container ->
                container.create("mavenJava", MavenPublication::class.java) { publication ->
                    publication.artifactId = "meeko"

                    publication.from(project.components.getByName("java"))

                    publication.pom { pom ->
                        pom.name.set("Meeko")
                        pom.description.set("Java Base Util")
                        pom.url.set("https://bus.raccoons.co/artefacts/meeko")

                        pom.licenses { licenseSpec ->
                            licenseSpec.license { pomLicense ->
                                pomLicense.name.set("MIT License")
                                pomLicense.url.set("https://opensource.org/licenses/MIT")
                            }
                        }
                    }
                }
            }
    }

    class Builder {
        fun build() = MavenPublishConfiguration()
    }
}
