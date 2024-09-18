plugins {
    alias(libs.plugins.winenote.android.library)
    alias(libs.plugins.winenote.android.library.compose)
}

android {
    namespace = "com.winenote.core.designsystem"
}

dependencies {
    implementation(projects.core.resource)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.lottie.compose)
    implementation(libs.timber)

    api(libs.androidx.compose.ui.tooling)
}