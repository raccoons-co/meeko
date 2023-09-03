package co.raccoons.local.gradle.javadoc

/**
 * The Javadoc tag.
 */
class JavadocTag(private val name: String, private val head: String) {

    /** Returns Javadoc tag string representation. */
    override fun toString() = "$name:a:$head:"
}
