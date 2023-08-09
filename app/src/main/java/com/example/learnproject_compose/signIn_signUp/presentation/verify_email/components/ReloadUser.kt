package com.example.learnproject_compose.signIn_signUp.presentation.verify_email.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnproject_compose.signIn_signUp.componente.ProgressBar
import com.example.learnproject_compose.signIn_signUp.presentation.Profile.ProfileViewModel
import com.example.learnproject_compose.signIn_signUp.util.Response

@Composable
fun ReloadUser(viewModel: ProfileViewModel = hiltViewModel(), navigateToProfileScreen: () -> Unit) {
    when (val reloadUserResponses = viewModel.reloadUserResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            val isUserReload = reloadUserResponses.data
            LaunchedEffect(isUserReload) {
                if (isUserReload) {
                    navigateToProfileScreen()
                }
            }
        }

        is Response.Failure -> reloadUserResponses.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }

}