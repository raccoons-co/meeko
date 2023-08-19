/*
 * Copyright 2023, Raccoons. Developing simple way to change.
 *
 * @license MIT
 */

package co.raccoons.local.gradle.java

class TestImplementation(
    private val group: String,
    private val name: String,
    private val version: String,
) : Dependency("testImplementation", DependencyNotation(group, name, version)) {

}