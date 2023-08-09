package com.example.learnproject_compose.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

@Composable
fun ProvideAppUtils(
    dimensions: Dimensions,
    orientation: Orientation,
    context: @Composable () -> Unit
) {

    val dimSet = remember { dimensions }
    val orientation = remember { orientation }

    CompositionLocalProvider(
        LocalAppDimens provides dimSet,
        LocalorientationMode provides orientation,
        content = context
    )

}

val LocalAppDimens = compositionLocalOf {
    smallDimensions
}

val LocalorientationMode = compositionLocalOf {
    Orientation.Portroit
}