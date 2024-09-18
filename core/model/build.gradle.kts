plugins {
    alias(libs.plugins.winenote.jvm.library)
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.kotlinx.collections.immutable)
    compileOnly(libs.compose.stable.marker)
}