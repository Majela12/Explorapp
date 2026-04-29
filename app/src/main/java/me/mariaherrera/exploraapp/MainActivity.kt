package me.mariaherrera.exploraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.mariaherrera.exploraapp.screens.HomeScreen
import me.mariaherrera.exploraapp.ui.elements.AddTouristicPlaceScreen
import me.mariaherrera.exploraapp.ui.elements.LoginScreen
import me.mariaherrera.exploraapp.ui.elements.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val myNavController = rememberNavController()

            NavHost(
                navController = myNavController,
                startDestination = "login",
                modifier = Modifier.fillMaxSize()
            ) {

                composable("login") {
                    LoginScreen(
                        onSuccessfulLogin = {
                            myNavController.navigate("home")
                        },
                        onNavigateToRegister = {
                            myNavController.navigate("register")
                        }
                    )
                }

                composable("register") {
                    RegisterScreen(
                        onRegisterSuccess = {},
                        onNavigateToLogin = {
                            myNavController.popBackStack()
                        },
                        onBackClick = {
                            myNavController.popBackStack()
                        }
                    )
                }

                composable("home") {
                    HomeScreen( onClickAddTouristicPlace ={
                        myNavController.navigate("add_touristic_place")
                    } )
                }


                composable("AddTouristicPlace") {
                    AddTouristicPlaceScreen()
                }
            }
        }
    }

}