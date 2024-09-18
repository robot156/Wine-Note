import com.winenote.convention.Plugins
import com.winenote.convention.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

internal class JvmLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.KOTLIN_JVM)
            }
            configureKotlinJvm()
        }
    }
}