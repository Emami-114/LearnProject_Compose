package com.example.learnproject_compose.screen.home.features

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Features(
    val title: String,
    val count: Int?,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color,
)

