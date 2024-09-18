package com.winenote.core.ui.util


fun CharSequence?.isFloat(): Boolean {
    return try {
        this?.toString()?.toFloat()
        true
    } catch (e: NumberFormatException) {
        false
    }
}