plugins {
    alias(libs.plugins.winenote.android.library)
    alias(libs.plugins.winenote.android.library.compose)
    id("kotlin-parcelize")
}

android {
    namespace = "com.winenote.core.ui"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.model)
    implementation(projects.core.resource)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.lottie.compose)
    implementation(libs.timber)
}