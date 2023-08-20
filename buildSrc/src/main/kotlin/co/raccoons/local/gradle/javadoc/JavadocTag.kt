package co.raccoons.local.gradle.javadoc

class JavadocTag(private val name: String, private val head: String) {

    override fun toString() = "$name:a:$head:"
}
