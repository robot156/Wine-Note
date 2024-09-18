plugins {
    alias(libs.plugins.winenote.android.feature)
}

android {
    namespace = "com.winenote.feature.main"
}

dependencies {
    implementation(projects.feature.splash)
    implementation(projects.feature.setting)
    implementation(projects.feature.wineBin)
    implementation(projects.feature.wineDetail)
    implementation(projects.feature.wineList)
    implementation(projects.feature.wineWrite)

    implementation(libs.androidx.core.splashScreen)
}