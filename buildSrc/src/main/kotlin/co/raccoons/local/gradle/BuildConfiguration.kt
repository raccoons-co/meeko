package co.raccoons.local.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildConfiguration private constructor() {

    companion object {
        fun of(project: Project): Builder {
            return Builder(project)
        }
    }

    class Builder(private val project: Project) {

        fun use(plugin: Plugin<Project>): Builder {
            plugin.apply(this.project)
            return this
        }
    }
}
