import com.winenote.convention.Plugins
import com.winenote.convention.implementation
import com.winenote.convention.ksp
import com.winenote.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.ANDROID_HILT)
                apply(Plugins.KSP)
            }

            dependencies {
                implementation(libs.hilt.android)
                ksp(libs.hilt.android.compiler)
            }
        }
    }
}