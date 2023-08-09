package com.example.learnproject_compose.signIn_signUp.presentation.Profile

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnproject_compose.signIn_signUp.componente.ProgressBar
import com.example.learnproject_compose.signIn_signUp.util.Response
import com.example.learnproject_compose.signIn_signUp.util.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RevokeAccess(
    viewModel: ProfileViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    signOut: () -> Unit
) {
    val context = LocalContext.current
    fun showRevokeAccessMessage() = coroutineScope.launch {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = "Sie müssen sich erneut authentifizieren, bevor Sie den Zugriff widerrufen.",
            actionLabel = "Abmelden"
        )
        if (result == SnackbarResult.ActionPerformed) {
            signOut()
        }
    }

    when (val revokeAccessResponses = viewModel.revokeAccessResponses) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            val isAccessRevoked = revokeAccessResponses.data
            LaunchedEffect(isAccessRevoked){
                if (isAccessRevoked){
                    Toast.showToast(context,"Ihr Zugriff wurde widerrufen.")
                }
            }
        }
        is Response.Failure -> revokeAccessResponses.apply {
            LaunchedEffect(e){
                print(e)
                if (e.message == "This operation is sensitive and requires recent authentication. Log in again before retrying this request."){
                    showRevokeAccessMessage()
                }
            }
        }
    }

}