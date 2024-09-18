plugins {
    alias(libs.plugins.winenote.jvm.library)
    alias(libs.plugins.winenote.jvm.hilt)
}

dependencies {
    // Kotlin
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines)

    // Paging
    implementation(libs.androidx.paging.common)
}