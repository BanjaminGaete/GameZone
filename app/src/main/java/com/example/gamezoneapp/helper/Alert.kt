package com.example.gamezoneapp.helper


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

//La alerta vista en clases deberia servirnos y comportarse exactamente igual.

@Composable
fun showAlert(
    titulo:String,
    mensaje: String,
    textoBtnConfirmar:String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
){
    AlertDialog(
        title = { Text(titulo) },
        text = {Text(mensaje)},
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = textoBtnConfirmar)
            }
        }
    )
}
@Composable
fun showConfirm(
    titulo:String,
    mensaje:String,
    textoBtnConfirmar:String,
    textoBtnCancelar:String,
    eventoConfirmar: () -> Unit,
    eventoCancelar: () -> Unit,
    eventoTerminarAlerta: () -> Unit
){
    AlertDialog(
        title = {Text(text = titulo)},
        text = {Text(text = mensaje)},
        onDismissRequest = eventoTerminarAlerta,
        confirmButton = {
            Button(onClick = eventoConfirmar) {
                Text(text = textoBtnConfirmar)
            }
        },
        dismissButton = {
            Button(onClick = eventoCancelar) {
                Text(text = textoBtnCancelar)
            }
        }
    )
}