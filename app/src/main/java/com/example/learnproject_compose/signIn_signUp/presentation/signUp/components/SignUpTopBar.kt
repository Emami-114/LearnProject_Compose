package com.example.learnproject_compose.signIn_signUp.presentation.signUp.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.signIn_signUp.componente.BackIcon
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.statusBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTopBar(title: String = "Registrieren", navigateBack: () -> Unit) {

    TopAppBar(title = {
        Text(text = title, color = TextWhite,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Medium
        )
                      },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            DeepBlue
        ), navigationIcon = {
            BackIcon(navigateBack = navigateBack)
        })

}