package co.raccoons.local.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion

class JavaVersionSetter(private val version: JavaLanguageVersion) : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.withType(JavaCompile::class.java) {
            it.sourceCompatibility = this.version.toString()
            it.targetCompatibility = this.version.toString()
        }
    }
}
