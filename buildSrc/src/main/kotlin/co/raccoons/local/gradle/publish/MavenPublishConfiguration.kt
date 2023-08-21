/*
 * Copyright 2023, Raccoons. Developing simple way to change.
 *
 * @license MIT
 */

package co.raccoons.local.gradle.publish

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension

private const val MAVEN_PUBLISH_PLUGIN_ID = "maven-publish"

class MavenPublishConfiguration private constructor(): Plugin<Project> {

    override fun apply(project: Project) {
        this.setupPlugin(project)
        this.addTaskPackageJavaSources(project)
        this.addTaskPackageJavadoc(project)
    }

    private fun setupPlugin(project: Project) {
        project.plugins.apply(MAVEN_PUBLISH_PLUGIN_ID)
    }

    private fun addTaskPackageJavaSources(project: Project) {
        project.extensions
            .getByType(JavaPluginExtension::class.java)
            .withSourcesJar()
    }

    private fun addTaskPackageJavadoc(project: Project) {
        project.extensions
            .getByType(JavaPluginExtension::class.java)
            .withJavadocJar()
    }

    class Builder {
        fun build() = MavenPublishConfiguration()
    }
}
