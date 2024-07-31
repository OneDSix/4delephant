package elephant

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.JavaExec

class ElephantPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("ElephantPlugin", ElephantExtension::class.java)

        project.afterEvaluate {
            configureDependencies(project, extension)
        }

        project.tasks.register("compileMyProject", org.gradle.api.tasks.compile.JavaCompile::class.java) { task ->
            task.source = project.fileTree("src/main/java")
            task.classpath = project.files()
            task.destinationDir = project.file("build/classes/java/main")
            task.options.encoding = "UTF-8"
            task.sourceCompatibility = "8"
            task.targetCompatibility = "8"
        }

        project.tasks.register("runMyProject", JavaExec::class.java) { task ->
            task.classpath = project.files("build/classes/java/main")
            task.main = "com.example.Main" // Adjust this to your main class
        }

        project.tasks.register("compileAndRun") { task ->
            task.dependsOn("compileMyProject")
            task.finalizedBy("runMyProject")
        }
    }

    private fun configureDependencies(project: Project, extension: ElephantExtension) {
        val version = when (extension.version) {
            "newest-release" -> "+"
            "newest-dev" -> "LATEST"
            else -> extension.version
        }

        extension.addons.forEach { (name, version) ->
            project.dependencies.add("implementation", "$name:$version")
        }
    }
}
