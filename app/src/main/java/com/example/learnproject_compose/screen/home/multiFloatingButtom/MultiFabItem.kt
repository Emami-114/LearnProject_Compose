package com.example.learnproject_compose.screen.home.multiFloatingButtom

import androidx.annotation.DrawableRes

data class MultiFabItem(
    val id: Int,
    @DrawableRes val iconRes: Int,
    val label: String = "",
    val onClick: () -> Unit
)
