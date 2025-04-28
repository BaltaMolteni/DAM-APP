package com.appsmoviles.tp1.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.appsmoviles.tp1.navigation.AppRoutes
import com.appsmoviles.tp1.ui.theme.Tp1Theme

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar Sesión", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            if (username == "Juan Torres" && password == "1234utn") {
                Toast.makeText(context, "Login exitoso.", Toast.LENGTH_SHORT).show()
                navController.navigate(AppRoutes.HOME)
            } else {
                Toast.makeText(context, "Datos incorrectos.", Toast.LENGTH_SHORT).show()
            }
        },
            modifier = Modifier
                .fillMaxWidth(0.75f)
        ) {
            Text("Ingresar")
        }
        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {
            navController.navigate(AppRoutes.REGISTER)
        },
            modifier= Modifier
                .fillMaxWidth(0.75f))
        {
            Text("Registrarse")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Tp1Theme {
        LoginScreen(navController = rememberNavController())
    }
}