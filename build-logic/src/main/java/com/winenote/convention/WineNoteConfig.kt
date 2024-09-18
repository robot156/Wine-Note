package com.winenote.convention

import org.gradle.api.JavaVersion

@Suppress("unused")
object WineNoteConfig {

    const val APPLICATION_ID = "com.winenote"

    const val MIN_SDK = 28    // os 9
    const val TARGET_SDK = 34 // os 14
    const val COMPILE_SDK = 34
    val javaCompileTarget = JavaVersion.VERSION_17

    private const val VERSION_MAJOR = 1
    private const val VERSION_MINOR = 0
    private const val VERSION_PATCH = 0

    const val VERSION_NAME = "$VERSION_MAJOR.$VERSION_MINOR.$VERSION_PATCH"
    const val VERSION_CODE = VERSION_MAJOR.times(1000000) + VERSION_MINOR.times(1000) + VERSION_PATCH
}