
plugins {
    alias(libs.plugins.winenote.android.application)
    alias(libs.plugins.winenote.android.application.compose)
    alias(libs.plugins.winenote.android.hilt)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.winenote"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.designsystem)
    implementation(projects.core.resource)
    implementation(projects.core.model)
    implementation(projects.core.ui)

    implementation(projects.feature.main)
    implementation(projects.feature.setting)
    implementation(projects.feature.splash)
    implementation(projects.feature.wineBin)
    implementation(projects.feature.wineDetail)
    implementation(projects.feature.wineList)
    implementation(projects.feature.wineWrite)

    // AndroidX
    implementation(libs.androidx.startup)

    // Kotlin
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)

    // Image Loader
    implementation(libs.bundles.coil)

    // Log tracker
    implementation(libs.timber)
}