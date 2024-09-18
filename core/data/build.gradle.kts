import com.winenote.convention.WineNoteConfig

plugins {
    alias(libs.plugins.winenote.android.library)
    alias(libs.plugins.winenote.android.hilt)
    id("com.google.devtools.ksp")
}

android {

    namespace = "com.winenote.core.data"

    defaultConfig {
        buildConfigField("String", "VERSION_NAME", "\"${WineNoteConfig.VERSION_NAME}\"")
    }
}

dependencies {
    implementation(projects.core.domain)

    // AndroidX DataStore
    implementation(libs.androidx.dataStore)

    // AndroidX Room
    implementation(libs.bundles.androidx.room)
    ksp(libs.androidx.room.compiler)

    // Kotlin
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines)

    // Moshi
    implementation(libs.moshi.core)
    implementation(libs.moshi.adapter)
    ksp(libs.moshi.codegen)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.storage)
    implementation(libs.compressor)

    // Log tracker
    implementation(libs.timber)
}