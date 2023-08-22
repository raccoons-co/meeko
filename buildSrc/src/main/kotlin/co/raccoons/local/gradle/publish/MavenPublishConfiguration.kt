/*
 * Copyright 2023, Raccoons. Developing simple way to change.
 *
 * @license MIT
 */

package co.raccoons.local.gradle.publish

import co.raccoons.local.gradle.publish.maven.PublicationProto.Publication
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication

private const val MAVEN_PUBLISH_PLUGIN_ID = "maven-publish"

class MavenPublishConfiguration private constructor(
    private val publication: Publication
) : Plugin<Project> {

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
                    publication.artifactId = this.publication.artifactId

                    publication.from(project.components.getByName("java"))

                    publication.pom { pom ->
                        pom.name.set("Meeko")
                        pom.description.set("Java Base Util")
                        pom.url.set("https://bus.raccoons.co/artefacts/meeko")

                        pom.licenses { spec ->
                            spec.license { pomLicense ->
                                pomLicense.name.set(this.publication.pomLicense.name)
                                pomLicense.url.set(this.publication.pomLicense.url)
                            }
                        }
                    }
                }
            }
    }

    companion object {
        fun newBuilder(): MavenPublishConfigurationBuilder {

            class Builder : MavenPublishConfigurationBuilder {

                private var publication = Publication.newBuilder().build()

                override fun setPublication(publication: Publication): MavenPublishConfigurationBuilder{
                    return this
                }

                override fun build(): MavenPublishConfiguration {
                    return MavenPublishConfiguration(this.publication)
                }
            }

            return Builder()
        }

        interface MavenPublishConfigurationBuilder {

            fun setPublication(publication: Publication): MavenPublishConfigurationBuilder

            fun build(): MavenPublishConfiguration
        }
    }
}
