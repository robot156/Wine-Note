plugins {
    alias(libs.plugins.winenote.android.feature)
}

android {
    namespace = "com.winenote.feature.winewrite"
}

dependencies {
    implementation(libs.compose.ratingbar)
}
