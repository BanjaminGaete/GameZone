package com.example.gamezoneapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamezoneapp.models.Producto
import com.example.gamezoneapp.viewmodel.ProductoViewModel
import androidx.compose.material3.*
import com.example.gamezoneapp.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarProductoScreen(
    navController: NavController,
    productoViewModel: ProductoViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precioTexto by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    val imagenesDisponibles = listOf(
        "Dante's Inferno" to R.drawable.dantesinferno,
        "God of War" to R.drawable.godofwar,
        "DarkSouls III" to R.drawable.darksouls,
        "Guitar Hero III" to R.drawable.guitarhero,
        "Half Life II" to R.drawable.halflife,
        "Need For Speed: Underground II" to R.drawable.nfs,
        "Silent Hill II" to R.drawable.silenthill,
        "Silent Hill III" to R.drawable.silenthilll,
        "Winning Eleven Chilean League" to R.drawable.winningeleven,
        "Resident Evil 4" to R.drawable.re

    )

    var imagenSeleccionada by remember { mutableStateOf(imagenesDisponibles[0]) }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )

        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") }
        )

        TextField(
            value = precioTexto,
            onValueChange = { precioTexto = it },
            label = { Text("Precio") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = imagenSeleccionada.first,
                onValueChange = {},
                readOnly = true,
                label = { Text("Imagen del producto") },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                imagenesDisponibles.forEach { imagen ->
                    DropdownMenuItem(
                        text = { Text(imagen.first) },
                        onClick = {
                            imagenSeleccionada = imagen
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val precio = precioTexto.toDoubleOrNull()
            if (nombre.isBlank() || descripcion.isBlank() || precio == null) {
                error = "Completa todos los campos correctamente"
            } else {
                productoViewModel.agregarProducto(
                    Producto(
                        nombre = nombre,
                        descripcion = descripcion,
                        precio = precio,
                        imagenResId = imagenSeleccionada.second
                    )
                )
                navController.popBackStack()
            }
        }) {
            Text("Guardar producto")
        }

        if (error.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }
    }
}
