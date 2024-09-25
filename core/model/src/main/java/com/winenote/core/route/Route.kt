package com.winenote.core.route

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Splash : Route

    @Serializable
    data object Setting : Route

    @Serializable
    data object WineList : Route

    @Serializable
    data object WineBin : Route

    @Serializable
    data class WineDetail(val recordId: String) : Route

    @Serializable
    data class WineWrite(val recordId: String?) : Route
}