package com.example.learnproject_compose.screen.home.multiFloatingButtom

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.TextWhite


@Immutable
interface FabOption {
    @Stable
    val iconTint: Color

    @Stable
    val backgroundTint: Color

    @Stable
    val showLabel: Boolean
}

private class FabOptionImpl(
    override val iconTint: Color,
    override val backgroundTint: Color,
    override val showLabel: Boolean

) : FabOption

@SuppressLint("ComposableNaming")
@Composable
fun FabOption(
    backgroundTint: Color = LightGreen3,
    iconTint: Color = TextWhite,
    showLabel: Boolean = false
): FabOption = FabOptionImpl(iconTint, backgroundTint, showLabel)