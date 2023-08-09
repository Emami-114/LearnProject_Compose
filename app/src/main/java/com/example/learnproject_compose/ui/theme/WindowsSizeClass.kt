package com.example.learnproject_compose.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration

sealed class WindowsSize(val size: Int) {
    data class Small(val smallSize: Int) : WindowsSize(smallSize)
    data class Compact(val compactSize: Int) : WindowsSize(compactSize)
    data class Medium(val mediumSize: Int) : WindowsSize(mediumSize)
    data class Large(val largeSize: Int) : WindowsSize(largeSize)
}

data class WindowsSizeClass(
    val width: WindowsSize,
    val height: WindowsSize
)

@Composable
fun rememberWindowSizeClass(): WindowsSizeClass {
    val config = LocalConfiguration.current

    val width by remember(config) {
        mutableIntStateOf(config.screenWidthDp)
    }

    val height by remember(config) { mutableIntStateOf(config.screenHeightDp) }

    val windowsWidthClass = when {
        width <= 360 -> WindowsSize.Small(width)
        width in 361..480 -> WindowsSize.Compact(width)
        width in 481..720 -> WindowsSize.Medium(width)
        else -> WindowsSize.Large(width)
    }

    val windowsHeightClass = when {
        height <= 360 -> WindowsSize.Small(height)
        height in 361..480 -> WindowsSize.Compact(height)
        height in 481..720 -> WindowsSize.Medium(height)
        else -> WindowsSize.Large(height)
    }

    return WindowsSizeClass(windowsWidthClass, windowsHeightClass)

}