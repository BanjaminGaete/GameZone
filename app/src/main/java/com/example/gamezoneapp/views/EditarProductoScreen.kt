package com.example.gamezoneapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


@Composable
fun EditarProductoScreen(
    productoId: Int,
    productoViewModel: ProductoViewModel,
    navController: NavController
) {
    val producto by productoViewModel.obtenerProductoPorId(productoId).collectAsState(initial = null)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    var confirmado by remember { mutableStateOf(false) }

    producto?.let {
        var nombre by remember { mutableStateOf(it.nombre) }
        var descripcion by remember { mutableStateOf(it.descripcion) }
        var precio by remember { mutableStateOf(it.precio.toString()) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
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

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = {
                    if (vibrator.hasVibrator()) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            vibrator.vibrate(
                                VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)
                            )
                        } else {
                            vibrator.vibrate(200)
                        }
                    }

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

                Spacer(modifier = Modifier.height(16.dp))

                AnimatedVisibility(
                    visible = confirmado,
                    enter = fadeIn(animationSpec = tween(500)),
                    exit = fadeOut(animationSpec = tween(1000))
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Cambios guardados",
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
        }
    }
}