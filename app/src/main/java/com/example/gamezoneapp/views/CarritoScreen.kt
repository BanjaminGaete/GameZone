package com.example.gamezoneapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamezoneapp.viewmodel.ProductoViewModel

@Composable
fun CarritoScreen(navController: NavController, productoViewModel: ProductoViewModel) {
    val carrito by productoViewModel.carrito.collectAsState()

    val total = carrito.sumOf { it.precio * it.cantidad }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Carrito de compras", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        carrito.forEach { item ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(item.nombre, style = MaterialTheme.typography.titleMedium)
                    Text("Cantidad: ${item.cantidad}")
                    Text("Subtotal: $${item.precio * item.cantidad}")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Total: $${total}", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            productoViewModel.vaciarCarrito()
            navController.popBackStack()
        }) {
            Text("Finalizar compra")
        }
    }
}
