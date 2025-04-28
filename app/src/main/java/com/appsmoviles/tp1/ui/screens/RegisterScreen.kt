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
import androidx.navigation.compose.rememberNavController // Para la Preview
import com.appsmoviles.tp1.navigation.AppRoutes
import com.appsmoviles.tp1.ui.theme.Tp1Theme

@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Registrarse", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Repetir Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .padding(bottom=13.dp)
                .fillMaxWidth(0.80f),

            onClick = {
            if (name.isEmpty()) {
                Toast.makeText(context, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
                return@Button
            }
            if (email.isEmpty()) {
                Toast.makeText(context, "El email no puede estar vacío", Toast.LENGTH_SHORT).show()
                return@Button
            }
            if (password.length < 6) {
                Toast.makeText(context, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return@Button
            }
            if (password != confirmPassword) {
                Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@Button
            }

            Toast.makeText(context, "Registro exitoso. Por favor, inicia sesión.", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
        ) {
            Text("Registrarse")
        }

        TextButton(onClick = {
            navController.navigate(AppRoutes.LOGIN)
        },
            modifier= Modifier
                .fillMaxWidth(0.75f))
        {
            Text("Volver")
        }
    }
}

// Preview para RegisterScreen
@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    Tp1Theme {
        RegisterScreen(navController = rememberNavController())
    }
}