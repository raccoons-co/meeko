/*
 * Copyright 2023, Raccoons. Developing simple way to change.
 *
 * @license MIT
 */

package co.raccoons.local.gradle.java

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * The scopes of the dependencies.
 */
internal class DependencyConfiguration : Plugin<Project> {

    private val dependencyScopes = mutableMapOf<String, MutableList<String>>()

    /** @inheritDoc */
    override fun apply(project: Project) {
        for ((configurationScope, dependencies) in dependencyScopes) {
            for (dependency in dependencies) {
                project.dependencies.add(configurationScope, dependency)
            }
        }
    }

    /** Adds dependency to the corresponding configuration scope. */
    fun add(dependency: Dependency): DependencyConfiguration {
        this.dependencyScopes
            .getOrPut(dependency.configurationName()) {
                mutableListOf()
            }
            .add(dependency.dependencyNotation().toString())

        return this
    }
}
