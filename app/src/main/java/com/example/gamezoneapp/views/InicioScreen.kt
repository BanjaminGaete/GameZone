package com.example.gamezoneapp.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamezoneapp.viewmodel.ProductoViewModel
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import com.example.gamezoneapp.R



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InicioScreen(navController: NavController, productoViewModel: ProductoViewModel) {
    val productos by productoViewModel.productos.collectAsState()
    BackHandler { }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { navController.navigate("perfil") }) {
                Icon(Icons.Default.Person, contentDescription = "Perfil")
            }

            Button(onClick = { navController.navigate("carrito") }) {
                Text("Ver carrito")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.gamezone),
                contentDescription = "Logo GameZone",
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit // ✅ evita pinning
            )
        }

        Text(
            "Productos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(productos) { producto ->
                var bounceScale by remember { mutableStateOf(1f) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Image(
                            painter = painterResource(id = producto.imagenResId),
                            contentDescription = producto.nombre,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                        Text(producto.descripcion, maxLines = 2)
                        Text("$${producto.precio}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                productoViewModel.agregarAlCarrito(producto)
                                bounceScale = 1.2f
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .graphicsLayer {
                                    scaleX = bounceScale
                                    scaleY = bounceScale
                                },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddShoppingCart,
                                contentDescription = "Agregar",
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Agregar")
                        }

                        LaunchedEffect(bounceScale) {
                            if (bounceScale > 1f) {
                                animate(
                                    initialValue = bounceScale,
                                    targetValue = 1f,
                                    animationSpec = tween(durationMillis = 300, easing = EaseOutBounce)
                                ) { value, _ ->
                                    bounceScale = value
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

