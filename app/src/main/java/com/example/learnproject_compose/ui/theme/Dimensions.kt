package com.example.learnproject_compose.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val small: Dp,
    val smallMedium: Dp,
    val medium: Dp,
    val mediumLarge: Dp,
    val large: Dp
)

val smallDimensions = Dimensions(
    small = 3.dp,
    smallMedium = 8.dp,
    medium = 12.dp,
    mediumLarge = 18.dp,
    large = 30.dp
)

val compactDimensions = Dimensions(
    small = 5.dp,
    smallMedium = 13.dp,
    medium = 20.dp,
    mediumLarge = 30.dp,
    large = 45.dp
)

val mediumDimensions = Dimensions(
    small = 8.dp,
    smallMedium = 16.dp,
    medium = 25.dp,
    mediumLarge = 38.dp,
    large = 60.dp
)

val largeDimensions = Dimensions(
    small = 12.dp,
    smallMedium = 19.dp,
    medium = 30.dp,
    mediumLarge = 45.dp,
    large = 80.dp
)