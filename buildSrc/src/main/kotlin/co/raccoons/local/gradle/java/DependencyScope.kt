/*
 * Copyright 2023, Raccoons. Developing simple way to change.
 *
 * @license MIT
 */

package co.raccoons.local.gradle.java

import org.gradle.api.Plugin
import org.gradle.api.Project

class DependencyScope : Plugin<Project> {

    private val dependencies = mutableMapOf<String, MutableList<String>>()

    override fun apply(project: Project) {
        for ((configurationName, configurationDependencies) in dependencies) {
            for (dependency in configurationDependencies) {
                project.dependencies.add(configurationName, dependency)
            }
        }
    }

    fun add(dependency: Dependency): DependencyScope {
        this.dependencies.getOrPut(dependency.configurationName()) {
            mutableListOf()
        }.add(dependency.dependencyNotation().toString())

        return this
    }
}
