package com.appsmoviles.tp1.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.appsmoviles.tp1.R
import com.appsmoviles.tp1.navigation.AppRoutes
import com.appsmoviles.tp1.ui.theme.Tp1Theme
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen(navController: NavController) {
    var selectedPlatform by remember {
        mutableStateOf("")
    }
    var selectedPreferences by remember {
        mutableStateOf(mutableSetOf<String>())
    }
    var otherPreferenceText by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(70.dp))

        Text(
            text = "Bienvenido!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text("Seleccioná tu plataforma:")
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedPlatform == "Android",
                onClick = { selectedPlatform = "Android" }
            )
            Text("Android")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = selectedPlatform == "iOS",
                onClick = { selectedPlatform = "iOS" }
            )
            Text("iOS")
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (selectedPlatform == "Android") {
            Image(
                painter = painterResource(id = R.drawable.androidrobot),
                contentDescription = "Logo Android",
                modifier = Modifier.size(100.dp)
            )
        } else if (selectedPlatform == "iOS") {
            Image(
                painter = painterResource(id = R.drawable.applelogo),
                contentDescription = "Logo iOS",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Seleccioná tus preferencias:")
        val options = listOf("Programación", "Redes", "Seguridad", "Hardware", "Otra")
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = selectedPreferences.contains(option),
                    onCheckedChange = { isChecked ->
                        selectedPreferences = if (isChecked) {
                            selectedPreferences.toMutableSet().apply { add(option) }
                        } else {
                            selectedPreferences.toMutableSet().apply { remove(option) }
                        }
                    }
                )
                Text(text = option)
            }
        }

        if ("Otra" in selectedPreferences) {
            OutlinedTextField(
                value = otherPreferenceText,
                onValueChange = { otherPreferenceText = it },
                label = { Text("Ingresá tu preferencia") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button( onClick = {
            Toast.makeText(context, "Preferencias enviadas con éxito.", Toast.LENGTH_SHORT).show() },
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(0.80f)
                .align(Alignment.CenterHorizontally)
        ){
            Text("Enviar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button( onClick = {
            navController.navigate(AppRoutes.LOGIN) {
                popUpTo(AppRoutes.HOME) { inclusive = true }
            }
        },
            colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red
        ),
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(0.40f)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Cerrar Sesión")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Tp1Theme {
        HomeScreen(navController = rememberNavController())
    }
}