plugins {
    alias(libs.plugins.winenote.jvm.library)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)
    compileOnly(libs.compose.stable.marker)
}