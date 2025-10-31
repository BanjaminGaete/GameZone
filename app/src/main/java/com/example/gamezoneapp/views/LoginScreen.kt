package com.example.gamezoneapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gamezoneapp.helper.showAlert
import com.example.gamezoneapp.helper.showConfirm
import com.example.gamezoneapp.viewmodel.LoginViewModel
import com.example.gamezoneapp.viewmodel.ProductoViewModel

/*@Composable
fun LoginScreen(navController: NavController, productoViewModel: ProductoViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Iniciar sesión", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                when {
                    username == "admin" && password == "admin" -> {
                        navController.navigate("admin")
                    }
                    username == "usuario" && password == "usuario" -> {
                        navController.navigate("inicio")
                    }
                    else -> {
                        error = true
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }

        if (error) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Credenciales inválidas", color = MaterialTheme.colorScheme.error)
        }
    }
}
*/

@Composable
fun LoginScreen(navController: NavController, productoViewModel: ProductoViewModel) {
    val viewModel = viewModel<LoginViewModel>()
    val correo = viewModel.loginViewModel.correo
    val contrasena = viewModel.loginViewModel.contrasena

    val nav = viewModel.deberiamosNavegar

    if(nav == true){
        navController?.navigate("inicio")
        viewModel.cambiarEstadoNavegacion()
    }

    val navAdmin = viewModel.deberiamosNavegarAdmin

    if(navAdmin == true){
        navController?.navigate("admin")
        viewModel.cambiarEstadoNavegacionAdmin()
    }


    if(viewModel.mostrarAlerta == true){
        showAlert(
            titulo = viewModel.tituloAlerta,
            mensaje = viewModel.mensajeAlerta,
            onDismiss = {viewModel.descartarAlerta()},
            onConfirm = {viewModel.descartarAlerta()},
            textoBtnConfirmar = viewModel.textoBotonAlerta
        )
    }

    if(viewModel.mostrarConfirmar == true){
        showConfirm(
            titulo = viewModel.tituloConfirmar,
            mensaje = viewModel.mensajeConfirmar,
            textoBtnCancelar = viewModel.textoBtnCancelar,
            textoBtnConfirmar = viewModel.textoBtnConfirmar,
            eventoCancelar = {viewModel.btnCancelarConfirmar()},
            eventoConfirmar = {viewModel.btnAceptarConfirmar()},
            eventoTerminarAlerta = {viewModel.terminarConfirmar()}
        )
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Iniciar sesión", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = {viewModel.cambioCorreo(it)},
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = {viewModel.cambioContrasena(it)},
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {viewModel.auth()},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }
    }
}
