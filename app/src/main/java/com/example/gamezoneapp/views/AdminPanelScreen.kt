package com.example.gamezoneapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamezoneapp.models.Producto
import com.example.gamezoneapp.viewmodel.ProductoViewModel

@Composable
fun AdminPanelScreen(
    navController: NavController,
    productoViewModel: ProductoViewModel
) {
    val productos by productoViewModel.productos.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Panel de Administración", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { productoViewModel.agregarProductoDemo() }) {
            Text("Agregar producto demo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(productos) { producto ->
                ProductoItem(producto = producto, onEliminar = {
                    productoViewModel.eliminarProducto(producto)
                })
            }
        }
    }
}

@Composable
fun ProductoItem(producto: Producto, onEliminar: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = producto.nombre, style = MaterialTheme.typography.titleMedium)
            Text(text = producto.descripcion, style = MaterialTheme.typography.bodyMedium)
            Text(text = "$${producto.precio}", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = onEliminar) {
                Text("Eliminar")
            }
        }
    }
}
