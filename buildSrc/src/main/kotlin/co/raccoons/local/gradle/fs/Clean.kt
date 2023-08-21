/*
 * Copyright 2023, Raccoons. Developing simple way to change.
 *
 * @license MIT
 */

package co.raccoons.local.gradle.fs

import org.gradle.api.Project

fun clean(project: Project): Project {
    project.buildDir.deleteRecursively()
    return project
}
