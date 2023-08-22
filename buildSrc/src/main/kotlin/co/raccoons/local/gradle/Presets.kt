/*
 * Copyright 2023, Raccoons. Developing simple way to change.
 *
 * @license MIT
 */

package co.raccoons.local.gradle

/**
 * Presets for plugins versions.
 */
enum class Presets(private val version: String) {
    CHECKSTYLE("10.12.2");

    fun version() = version
}
