package co.raccoons.local.gradle.javadoc

class JavadocTag(private val name: String, private val head: String) {

    /** Returns string representation of the Javadoc tag.  */
    override fun toString() = "$name:a:$head:"
}
