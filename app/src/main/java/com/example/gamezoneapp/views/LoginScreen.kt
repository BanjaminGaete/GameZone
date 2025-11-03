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

@Composable
fun LoginScreen(navController: NavController, productoViewModel: ProductoViewModel) {
    val viewModel = viewModel<LoginViewModel>()
    val correo = viewModel.loginViewModel.correo
    val contrasena = viewModel.loginViewModel.contrasena


    if (viewModel.deberiamosNavegar) {
        navController.navigate("inicio") {
            popUpTo("login") { inclusive = true }
        }
        viewModel.cambiarEstadoNavegacion()
    }


    if (viewModel.deberiamosNavegarAdmin) {
        navController.navigate("admin") {
            popUpTo("login") { inclusive = true }
        }
        viewModel.cambiarEstadoNavegacionAdmin()
    }


    if (viewModel.mostrarAlerta) {
        showAlert(
            titulo = viewModel.tituloAlerta,
            mensaje = viewModel.mensajeAlerta,
            onDismiss = { viewModel.descartarAlerta() },
            onConfirm = { viewModel.descartarAlerta() },
            textoBtnConfirmar = viewModel.textoBotonAlerta
        )
    }


    if (viewModel.mostrarConfirmar) {
        showConfirm(
            titulo = viewModel.tituloConfirmar,
            mensaje = viewModel.mensajeConfirmar,
            textoBtnCancelar = viewModel.textoBtnCancelar,
            textoBtnConfirmar = viewModel.textoBtnConfirmar,
            eventoCancelar = { viewModel.btnCancelarConfirmar() },
            eventoConfirmar = { viewModel.confirmarLogin() },
            eventoTerminarAlerta = {  }
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Iniciar sesión", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { viewModel.cambioCorreo(it) },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { viewModel.cambioContrasena(it) },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.auth() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }
    }
}
