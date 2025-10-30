package com.example.gamezoneapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamezoneapp.viewmodel.ProductoViewModel
import androidx.compose.runtime.*


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun EditarProductoScreen(
    productoId: Int,
    productoViewModel: ProductoViewModel,
    navController: NavController
) {
    val producto by productoViewModel.obtenerProductoPorId(productoId).collectAsState(initial = null)
    val scope = rememberCoroutineScope()
    var confirmado by remember { mutableStateOf(false) }

    producto?.let {
        var nombre by remember { mutableStateOf(it.nombre) }
        var descripcion by remember { mutableStateOf(it.descripcion) }
        var precio by remember { mutableStateOf(it.precio.toString()) }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            Text("Editar producto", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val productoActualizado = it.copy(
                    nombre = nombre,
                    descripcion = descripcion,
                    precio = precio.toDoubleOrNull() ?: it.precio
                )
                productoViewModel.actualizarProducto(productoActualizado)
                confirmado = true

                scope.launch {
                    delay(2000)
                    confirmado = false
                    navController.popBackStack()
                }
            }) {
                Text("Guardar cambios")
            }

            AnimatedVisibility(
                visible = confirmado,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Confirmado",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Cambios guardados", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}