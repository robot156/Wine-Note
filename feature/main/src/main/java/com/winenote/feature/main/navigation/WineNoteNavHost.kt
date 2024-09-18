package com.winenote.feature.main.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.winenote.feature.setting.navigateToSetting
import com.winenote.feature.setting.settingNavigation
import com.winenote.feature.splash.splashNavigation
import com.winenote.feature.splash.splashRoute
import com.winenote.feature.winebin.navigateToWineBin
import com.winenote.feature.winebin.wineBinNavigation
import com.winenote.feature.winedetail.navigateToWineDetail
import com.winenote.feature.winedetail.wineDetailNavigation
import com.winenote.feature.winelist.navigateToWineList
import com.winenote.feature.winelist.wineListNavigation
import com.winenote.feature.winewrite.navigateToWineWrite
import com.winenote.feature.winewrite.wineWriteNavigation

@Composable
fun WineNoteNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = splashRoute,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        navController = navController,
        startDestination = startDestination,
    ) {
        splashNavigation(
            navigateToWineList = navController::navigateToWineList
        )

        wineListNavigation(
            navigateToWineDetail = navController::navigateToWineDetail,
            navigateToWineWrite = navController::navigateToWineWrite,
            navigateToSetting = navController::navigateToSetting
        )

        wineWriteNavigation(
            navigateToWineDetail = navController::navigateToWineDetail,
            navigateToBack = navController::navigateUp
        )

        wineDetailNavigation(
            navigateToWineWrite = navController::navigateToWineWrite,
            navigateToBack = navController::navigateUp
        )

        settingNavigation(
            navigateToBinRecord = navController::navigateToWineBin,
            navigateToBack = navController::navigateUp
        )

        wineBinNavigation(
            navigateToBack = navController::navigateUp,
            navigateToWineDetail = navController::navigateToWineDetail
        )
    }
}