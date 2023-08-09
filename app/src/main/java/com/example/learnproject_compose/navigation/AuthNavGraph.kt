package com.example.learnproject_compose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.learnproject_compose.screen.bottomBar.BottomBarScreen
import com.example.learnproject_compose.signIn_signUp.presentation.forgot_password.ForgotPasswordScreen
import com.example.learnproject_compose.signIn_signUp.presentation.signIn.SignInScreen
import com.example.learnproject_compose.signIn_signUp.presentation.signUp.SignUpScreen
import com.example.learnproject_compose.signIn_signUp.presentation.verify_email.VerifyEmailScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(route = Graph.AUTHENTICATION, startDestination = AuthScreen.Login.route) {

        composable(route = AuthScreen.Login.route) {
            SignInScreen(navigateToForgotPasswordScreen = {
                navController.navigate(AuthScreen.Forgot.route)
            }
            ) {
                navController.navigate(AuthScreen.SignUp.route)
            }
        }

        composable(route = AuthScreen.Forgot.route) {
            ForgotPasswordScreen(navigateBack = { navController.navigateUp() })
        }

        composable(route = AuthScreen.SignUp.route) {
            SignUpScreen(navigateBack = { navController.popBackStack() })
        }

        composable(route = AuthScreen.Verify.route) {
            VerifyEmailScreen(navigationToProfileScreen = {
                navController.navigate(
                    Graph.HOME
                ) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            })
        }

    }
}

sealed class AuthScreen(val route: String){
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")
    object Verify : AuthScreen(route = "VERIFY")
}