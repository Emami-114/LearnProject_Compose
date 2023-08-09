package com.example.learnproject_compose.components.topBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.signIn_signUp.componente.BackIcon
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.statusBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSelb(
    title: String,
    style: TextStyle = androidx.compose.material.MaterialTheme.typography.h5,
    color: Color = DeepBlue,
    onNavigate: () -> Unit
) {
    TopAppBar(title = {
        Text(
            text = title,
            color = TextWhite,
            style = style,
            fontWeight = FontWeight.Medium
        )
    }, navigationIcon = {
        BackIcon {
            onNavigate.invoke()
        }
    },
        colors = TopAppBarDefaults
            .smallTopAppBarColors(containerColor = color)
    )


}


@Preview
@Composable
fun PreviewTopBar() {
    TopBarSelb(title = "Hallo") {}
}