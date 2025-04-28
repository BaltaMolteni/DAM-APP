package com.appsmoviles.tp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appsmoviles.tp1.navigation.AppRoutes
import com.appsmoviles.tp1.ui.screens.HomeScreen
import com.appsmoviles.tp1.ui.screens.LoginScreen
import com.appsmoviles.tp1.ui.screens.RegisterScreen
import com.appsmoviles.tp1.ui.theme.Tp1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tp1Theme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->


                    NavHost(
                        navController = navController,
                        startDestination = AppRoutes.LOGIN,
                        modifier = Modifier.padding(innerPadding) // Se aplica el innerPadding al NavHost
                    ) {
                        composable(AppRoutes.LOGIN) {
                            LoginScreen(navController = navController)
                        }
                        composable(AppRoutes.REGISTER) {
                            RegisterScreen(navController = navController)
                        }
                        composable(AppRoutes.HOME) {
                            HomeScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
