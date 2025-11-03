package com.example.gamezoneapp.views


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamezoneapp.viewmodel.PerfilViewModel


@Composable
fun PerfilScreen(navController: NavController, perfilViewModel: PerfilViewModel) {
    val nombre by perfilViewModel.nombre.collectAsState()
    val direccion by perfilViewModel.direccion.collectAsState()
    val contacto by perfilViewModel.contacto.collectAsState()

    val mostrarAlerta by perfilViewModel.mostrarAlerta.collectAsState()
    val tituloAlerta by perfilViewModel.tituloAlerta.collectAsState()
    val mensajeAlerta by perfilViewModel.mensajeAlerta.collectAsState()
    val textoBotonAlerta by perfilViewModel.textoBotonAlerta.collectAsState()

    val mostrarConfirmar by perfilViewModel.mostrarConfirmar.collectAsState()
    val tituloConfirmar by perfilViewModel.tituloConfirmar.collectAsState()
    val mensajeConfirmar by perfilViewModel.mensajeConfirmar.collectAsState()
    val textoBtnConfirmar by perfilViewModel.textoBtnConfirmar.collectAsState()
    val textoBtnCancelar by perfilViewModel.textoBtnCancelar.collectAsState()

    val datosGuardados by perfilViewModel.datosGuardados.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Perfil de Usuario", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { perfilViewModel.cambioNombre(it) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = direccion,
            onValueChange = { perfilViewModel.cambioDireccion(it) },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = contacto,
            onValueChange = { perfilViewModel.cambioContacto(it) },
            label = { Text("Número de contacto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { perfilViewModel.validarDatos() }) {
            Text("Guardar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("camara") }) {
            Text("Cambiar foto de perfil")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate("historial") }) {
            Text("Historial de compras")
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate("inicio") }) {
            Text("Volver")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (datosGuardados) {
            Text("Resumen de datos ingresados:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Nombre: $nombre")
            Text("Dirección: $direccion")
            Text("Contacto: $contacto")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate("login") {
                    popUpTo("perfil") { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Cerrar sesión", color = Color.White)
        }


        if (mostrarAlerta) {
            AlertDialog(
                onDismissRequest = { perfilViewModel.descartarAlerta() },
                confirmButton = {
                    Button(onClick = { perfilViewModel.descartarAlerta() }) {
                        Text(textoBotonAlerta)
                    }
                },
                title = { Text(tituloAlerta) },
                text = { Text(mensajeAlerta) }
            )
        }


        if (mostrarConfirmar) {
            AlertDialog(
                onDismissRequest = { perfilViewModel.cancelarConfirmacion() },
                confirmButton = {
                    Button(onClick = { perfilViewModel.confirmarGuardado() }) {
                        Text(textoBtnConfirmar)
                    }
                },
                dismissButton = {
                    Button(onClick = { perfilViewModel.cancelarConfirmacion() }) {
                        Text(textoBtnCancelar)
                    }
                },
                title = { Text(tituloConfirmar) },
                text = { Text(mensajeConfirmar) }
            )
        }
    }
}