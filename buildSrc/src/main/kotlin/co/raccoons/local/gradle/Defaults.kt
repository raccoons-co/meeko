package co.raccoons.local.gradle

enum class Defaults(private val version: String) {
    CHECKSTYLE("10.12.2");

    fun version(): String {
        return version
    }
}