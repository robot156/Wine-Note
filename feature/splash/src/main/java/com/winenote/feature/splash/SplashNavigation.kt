package com.winenote.feature.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val splashRoute = "splash_route"

fun NavGraphBuilder.splashNavigation(
    navigateToWineList: (NavOptions) -> Unit
) {
    composable(splashRoute) {
        SplashRoute(
            navigateToWineList = navigateToWineList
        )
    }
}

fun NavController.navigateToSplash(navOptions: NavOptions? = null) {
    this.navigate(splashRoute, navOptions)
}