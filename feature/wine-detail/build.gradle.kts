plugins {
    alias(libs.plugins.winenote.android.feature)
}

android {
    namespace = "com.winenote.feature.winedetail"
}

dependencies {
    implementation(libs.compose.ratingbar)
}