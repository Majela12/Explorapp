package me.mariaherrera.exploraapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import me.mariaherrera.exploraapp.screens.HomeScreen
import me.mariaherrera.exploraapp.LoginScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onSuccessfulLogin = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    // opcional: navController.navigate("register")
                }
            )
        }

        composable("home") {
            HomeScreen()
        }
    }
}