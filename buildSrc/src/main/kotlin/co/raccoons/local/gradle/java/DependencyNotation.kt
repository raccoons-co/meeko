/*
 * Copyright 2023, Raccoons. Developing simple way to change.
 *
 * @license MIT
 */

package co.raccoons.local.gradle.java

class DependencyNotation(
    private val group: String,
    private val name: String,
    private val version: String
) {

    override fun toString() = "$group:$name:$version"
}
