package com.winenote.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.SimpleColorFilter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty

@Composable
fun WineLottieAnimation(
    modifier: Modifier = Modifier,
    assetName: String = "animation_loading",
    iterations: Int = LottieConstants.IterateForever,
    animationSpeed: Float = 1f,
    animationEndProgress: Float = 1f,
    lottieColor: Color = MaterialTheme.colorScheme.primary,
    onAnimationEnd: () -> Unit = {}
) {
    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR_FILTER,
            value = SimpleColorFilter(lottieColor.toArgb()),
            "**"
        ),
    )
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.Asset(assetName.plus(".json")))
    val progress by animateLottieCompositionAsState(lottieComposition)

    Box(modifier = modifier.fillMaxSize()) {
        if (assetName.isNotEmpty()) {
            LottieAnimation(
                modifier = modifier.align(Alignment.Center),
                composition = lottieComposition,
                iterations = iterations,
                speed = animationSpeed,
                dynamicProperties = dynamicProperties
            )
        }
    }

    if (progress >= animationEndProgress && iterations != LottieConstants.IterateForever) {
        onAnimationEnd()
    }
}