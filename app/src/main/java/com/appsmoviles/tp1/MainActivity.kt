package com.appsmoviles.tp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.appsmoviles.tp1.navigation.AppRoutes
import com.appsmoviles.tp1.ui.screens.HomeScreen
import com.appsmoviles.tp1.ui.screens.LoginScreen
import com.appsmoviles.tp1.ui.screens.RegisterScreen
import com.appsmoviles.tp1.ui.theme.Tp1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tp1Theme { // Usa tu tema
                // Obtiene el NavController que manejará la navegación
                val navController = rememberNavController()

                // NavHost es el contenedor de navegación.
                // Necesita el navController y la ruta de inicio.
                NavHost(navController = navController, startDestination = AppRoutes.LOGIN) {

                    // Define el destino para la ruta LOGIN
                    composable(AppRoutes.LOGIN) {
                        // Llama a la función Composable de la pantalla de Login, pasándole el navController
                        LoginScreen(navController = navController)
                    }

                    // Define el destino para la ruta REGISTER
                    composable(AppRoutes.REGISTER) {
                        // Llama a la función Composable de la pantalla de Registro, pasándole el navController
                        RegisterScreen(navController = navController)
                    }

                    // Define el destino para la ruta HOME
                    // Usamos navArguments para especificar que la ruta HOME espera un argumento 'username'
                    composable(
                        route = AppRoutes.HOME,
                        arguments = listOf(navArgument("username") { type = NavType.StringType; nullable = true }) // Define el argumento
                    ) { backStackEntry ->
                        // Extrae el argumento 'username' del backStackEntry
                        val username = backStackEntry.arguments?.getString("username")
                        // Llama a la función Composable de la pantalla Home, pasándole el navController y el nombre de usuario
                        HomeScreen(navController = navController, username = username)
                    }

                    // Puedes agregar más destinos aquí para otras pantallas
                }
            }
        }
    }
}

// Preview principal (puede mostrar el inicio de la navegación o un indicador)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Tp1Theme {
        // En la preview de la Activity principal, a menudo solo muestras un indicador
        // o la pantalla de inicio si es simple.
        Text("Previsualizando MainActivity con NavHost...")
    }
}