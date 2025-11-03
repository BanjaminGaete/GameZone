package com.example.gamezoneapp.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamezoneapp.models.Producto
import com.example.gamezoneapp.viewmodel.ProductoViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color


@Composable
fun ProductoItem(
    producto: Producto,
    onEliminar: () -> Unit,
    onEditar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = producto.imagenResId),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = producto.nombre, style = MaterialTheme.typography.titleMedium)
            Text(text = producto.descripcion, style = MaterialTheme.typography.bodyMedium)
            Text(text = "$${producto.precio}", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onEditar) {
                    Icon(Icons.Filled.Edit, contentDescription = "Editar")
                    Spacer(Modifier.width(4.dp))
                    Text("Editar")
                }

                Button(onClick = onEliminar) {
                    Text("Eliminar")
                }
            }
        }
    }
}

@Composable
fun AdminPanelScreen(
    navController: NavController,
    productoViewModel: ProductoViewModel
) {
    val productos by productoViewModel.productos.collectAsState()


    BackHandler {

    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Panel de Administración", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { navController.navigate("agregar") }) {
                Text("Nuevo producto")
            }

            Button(
                onClick = {
                    navController.navigate("login") {
                        popUpTo("admin") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Cerrar sesión", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(productos) { producto ->
                ProductoItem(
                    producto = producto,
                    onEliminar = { productoViewModel.eliminarProducto(producto) },
                    onEditar = { navController.navigate("editar/${producto.id}") }
                )
            }
        }
    }
}


