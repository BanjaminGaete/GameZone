package com.example.gamezoneapp.views

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamezoneapp.viewmodel.ProductoViewModel
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CarritoScreen(navController: NavController, productoViewModel: ProductoViewModel) {
    val carrito by productoViewModel.carrito.collectAsState()
    val total = carrito.sumOf { it.precio * it.cantidad }

    val context = LocalContext.current
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    var mostrarCheck by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Carrito de compras", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        carrito.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = item.imagenResId),
                        contentDescription = item.nombre,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )

                    Spacer(modifier = Modifier.width(12.dp))


                    IconButton(onClick = {
                        val nuevaCantidad = item.cantidad - 1
                        if (nuevaCantidad > 0) {
                            val actualizado = item.copy(cantidad = nuevaCantidad)
                            productoViewModel.actualizarCantidadCarrito(actualizado)
                        } else {
                            productoViewModel.eliminarDelCarrito(item)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Eliminar unidad",
                            tint = Color.Red
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))


                    Column(modifier = Modifier.weight(1f)) {
                        Text(item.nombre, style = MaterialTheme.typography.titleMedium)
                        Text("Cantidad: ${item.cantidad}")
                        Text("Subtotal: $${item.precio * item.cantidad}")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Total: $${total}", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            if (vibrator.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)
                    )
                } else {
                    vibrator.vibrate(200)
                }
            }

            productoViewModel.vaciarCarrito()
            mostrarCheck = true
        }) {
            Text("Finalizar compra")
        }

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = mostrarCheck,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Compra realizada",
                tint = Color(0xFF4CAF50),
                modifier = Modifier.size(64.dp)
            )
        }

        if (mostrarCheck) {
            LaunchedEffect(Unit) {
                delay(2000)
                mostrarCheck = false
                navController.popBackStack("inicio", inclusive = false)
            }
        }
    }
}
