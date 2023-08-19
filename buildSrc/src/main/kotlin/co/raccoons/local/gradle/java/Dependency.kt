/*
 * Copyright 2023, Raccoons. Developing simple way to change.
 *
 * @license MIT
 */

package co.raccoons.local.gradle.java

abstract class Dependency(
    private val configurationName: String,
    private val dependencyNotation: DependencyNotation
) {

    fun configurationName(): String {
        return configurationName
    }

    fun dependencyNotation(): String {
        return dependencyNotation.toString()
    }
}