package com.example.learnproject_compose.signIn_signUp.componente

import androidx.compose.foundation.layout.size
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.TextWhite

@Composable
fun BackIcon(color: Color = TextWhite, navigateBack: () -> Unit) {
    IconButton(onClick = navigateBack) {
        Icon(
            imageVector = Icons.Outlined.ArrowBackIosNew,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(AppTheme.dimens.mediumLarge)
        )

    }

}