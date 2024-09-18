plugins {
    alias(libs.plugins.winenote.android.library)
}

android {
    namespace = "com.winenote.core.resource"
}

dependencies {
    implementation(libs.androidx.core.splashScreen)
}