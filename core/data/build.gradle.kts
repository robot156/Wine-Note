import com.winenote.convention.WineNoteConfig

plugins {
    alias(libs.plugins.winenote.android.library)
    alias(libs.plugins.winenote.android.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
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
    implementation(libs.kotlinx.serialization.json)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.storage)

    // image compress
    implementation(libs.compressor)

    // Log tracker
    implementation(libs.timber)
}