/*
 * Copyright 2023, Raccoons. Developing simple way to change.
 *
 * @license MIT
 */

package co.raccoons.local.gradle.java

private const val CONFIGURATION_NAME = "implementation"

class Implementation(
    group: String,
    name: String,
    version: String
) : Dependency(CONFIGURATION_NAME, DependencyNotation(group, name, version))
