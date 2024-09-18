import com.winenote.convention.WineNoteConfig

plugins {
    alias(libs.plugins.winenote.android.feature)
}

android {
    namespace = "com.winenote.feature.setting"

    defaultConfig {
        buildConfigField("String","VERSION_NAME","\"${WineNoteConfig.VERSION_NAME}\"")
    }
}