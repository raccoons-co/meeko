package co.raccoons.local.gradle

/**
 * Plugins version
 */
enum class Presets(private val version: String) {
    CHECKSTYLE("10.12.2");

    fun version() = version
}
