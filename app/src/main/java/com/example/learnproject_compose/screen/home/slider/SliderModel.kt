package com.example.learnproject_compose.screen.home.slider

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class SliderModel(
    val title: String,
    val desc: String,
    @DrawableRes
    val imageUri: Int,
)

data class SliderWordModel(
    val documentId: String? = null,
    val word: String? = null,
    val translate: String? = null,
    val level: String? = null,
)
data class SliderColor(
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color,
)
