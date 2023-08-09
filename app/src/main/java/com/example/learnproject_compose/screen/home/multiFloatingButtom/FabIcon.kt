package com.example.learnproject_compose.screen.home.multiFloatingButtom

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable


@Immutable
interface FabIcon {
    @Stable
    val iconRes: Int

    @Stable
    val iconRotate: Float?
}

private class FabiconImpl(
    override val iconRes: Int, override val iconRotate: Float?
) : FabIcon

fun FabIcon(@DrawableRes iconRes: Int, iconRotate: Float? = null): FabIcon =
    FabiconImpl(iconRes, iconRotate)