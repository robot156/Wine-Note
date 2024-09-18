package com.winenote.core.model

enum class WineColor {
    Sparkling,
    White,
    Rose,
    Red,
    Dessert
}

fun getWineColor(color: String): WineColor {
    return WineColor.entries.find { it.name == color } ?: WineColor.Red
}