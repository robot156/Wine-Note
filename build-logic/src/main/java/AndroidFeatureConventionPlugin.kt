import com.android.build.gradle.LibraryExtension
import com.winenote.convention.configureGradleManagedDevices
import com.winenote.convention.implementation
import com.winenote.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

internal class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("winenote.android.library")
                apply("winenote.android.library.compose")
                apply("winenote.android.hilt")
            }

            extensions.configure<LibraryExtension> {
                configureGradleManagedDevices(this)
            }

            dependencies {
                implementation(project(":core:domain"))
                implementation(project(":core:designsystem"))
                implementation(project(":core:ui"))
                implementation(project(":core:model"))
                implementation(project(":core:resource"))

                // AndroidX
                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.activity.compose)
                implementation(libs.bundles.androidx.lifecycle)

                // AndroidX Navigation
                implementation(libs.androidx.navigation.compose)

                // AndroidX Paging
                implementation(libs.androidx.paging.runtime)
                implementation(libs.androidx.paging.common)
                implementation(libs.androidx.paging.compose)

                // AndroidX Compose material3
                implementation(libs.androidx.compose.material3)
                implementation(libs.androidx.compose.material3.windowSizeClass)

                // AndroidX Hilt
                implementation(libs.androidx.hilt.common)
                implementation(libs.androidx.hilt.navigation.compose)

                // Kotlin
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.coroutines)
                implementation(libs.kotlinx.collections.immutable)

                // ETC
                implementation(libs.bundles.coil)
                implementation(libs.lottie.compose)
                implementation(libs.timber)
            }
        }
    }
}