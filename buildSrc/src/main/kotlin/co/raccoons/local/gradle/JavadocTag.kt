package co.raccoons.local.gradle

class JavadocTag(private val name: String, private val head: String) {

    override fun toString(): String {
        return "${name}:a:${head}:"
    }
}