package com.example.learnproject_compose.signIn_signUp.presentation.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnproject_compose.screen.topbar.TopBar

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState, topBar = {
        com.example.learnproject_compose.signIn_signUp.componente.TopBar(
            title = "ProfileScreen",
            signOut = {
                viewModel.signOut()
            }, revokeAccess = { viewModel.revokeAccess() })
    }, content = { padding ->
        ProfileContent(padding = padding)
    })

    RevokeAccess(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = { viewModel.signOut() })
}


@Composable
fun ProfileContent(
    padding: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding).background(Color.Gray)
            .padding(top = 48.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Wellcomen ",
            fontSize = 24.sp
        )
    }
}