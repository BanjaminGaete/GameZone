package com.example.gamezoneapp.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamezoneapp.R
import com.example.gamezoneapp.models.Producto

@Composable
fun HistorialScreen(navController: NavController? = null){

    var productos = listOf<Producto>(
        Producto(0,"Juego", "Desc", 1.1, R.drawable.godofwar),
        Producto(1,"Juego2", "Desc", 1.1, R.drawable.godofwar)
    )
    BackHandler {  }

    Column (modifier = Modifier.fillMaxSize()) {
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { navController?.navigate("perfil") }) {
                Text("Volver")
            }
        }

        LazyColumn {
            items(productos) {a ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ){
                        Image(
                            painter = painterResource(id=a.imagenResId),
                            contentDescription = "Imagen auto",
                            modifier = Modifier.height(60.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))

                        Column (modifier = Modifier.weight(1f)){
                            Text(text = "Nombre: ${a.nombre}")
                            Text(text = "Descripcion: ${a.descripcion}")
                            Text(text = "Precio: ${a.precio}")
                        }

                        Row{
                            IconButton(onClick = {}) {
                                Icon(Icons.Default.Edit,
                                    contentDescription = "Editar")
                            }
                            IconButton(onClick = {}) {
                                Icon(Icons.Default.Delete,
                                    contentDescription = "Eliminar")
                            }
                        }

                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun verHistorial(){
    HistorialScreen()
}