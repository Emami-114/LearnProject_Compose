package com.example.learnproject_compose.screen.home.slider

import androidx.annotation.DrawableRes

data class SliderModel(
    val title: String,
    val desc: String,
    @DrawableRes
    val imageUri: Int,
)
