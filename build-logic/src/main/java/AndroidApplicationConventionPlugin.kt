import com.android.build.api.dsl.ApplicationExtension
import com.winenote.convention.Plugins
import com.winenote.convention.WineNoteConfig
import com.winenote.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.ANDROID_APPLICATION)
                apply(Plugins.KOTLIN_ANDROID)
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                defaultConfig.apply {
                    applicationId = WineNoteConfig.APPLICATION_ID
                    targetSdk = WineNoteConfig.TARGET_SDK
                    versionCode = WineNoteConfig.VERSION_CODE
                    versionName = WineNoteConfig.VERSION_NAME
                }
            }
        }
    }
}