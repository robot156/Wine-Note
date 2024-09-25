package com.winenote.feature.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.winenote.core.route.Route

fun NavGraphBuilder.splashNavigation(
    navigateToWineList: (NavOptions) -> Unit
) {
    composable<Route.Splash> {
        SplashRoute(
            navigateToWineList = navigateToWineList
        )
    }
}

fun NavController.navigateToSplash(navOptions: NavOptions? = null) {
    this.navigate(Route.Splash, navOptions)
}