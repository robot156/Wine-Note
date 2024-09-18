import com.android.build.gradle.LibraryExtension
import com.winenote.convention.Plugins
import com.winenote.convention.WineNoteConfig
import com.winenote.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.ANDROID_LIBRARY)
                apply(Plugins.KOTLIN_ANDROID)
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = WineNoteConfig.TARGET_SDK
            }
        }
    }
}