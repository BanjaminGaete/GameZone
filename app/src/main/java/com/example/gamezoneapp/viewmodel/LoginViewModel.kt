package com.example.gamezoneapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gamezoneapp.models.LoginModel

class LoginViewModel: ViewModel() {
    var loginViewModel by mutableStateOf(LoginModel("",""))
        private set

    fun cambioCorreo(nuevoCorreo:String){
        loginViewModel = loginViewModel.copy(correo = nuevoCorreo)
    }

    fun cambioContrasena(nuevaContrasena:String){
        loginViewModel = loginViewModel.copy(contrasena = nuevaContrasena)
    }

    var mostrarAlerta by mutableStateOf(false)
        private set
    var tituloAlerta by mutableStateOf("")
        private set
    var mensajeAlerta by mutableStateOf("")
        private set
    var textoBotonAlerta by mutableStateOf("")
        private set

    fun descartarAlerta() {
        mostrarAlerta = false
    }

    // Confirmación
    var mostrarConfirmar by mutableStateOf(false)
        private set
    var tituloConfirmar by mutableStateOf("")
        private set
    var mensajeConfirmar by mutableStateOf("")
        private set
    var textoBtnConfirmar by mutableStateOf("")
        private set
    var textoBtnCancelar by mutableStateOf("")
        private set

    fun btnCancelarConfirmar() {
        mostrarConfirmar = false
    }

    // Navegación
    var deberiamosNavegar by mutableStateOf(false)
        private set
    var deberiamosNavegarAdmin by mutableStateOf(false)
        private set

    fun cambiarEstadoNavegacion() {
        deberiamosNavegar = false
    }

    fun cambiarEstadoNavegacionAdmin() {
        deberiamosNavegarAdmin = false
    }

    fun auth() {
        Log.d("Correo", loginViewModel.correo)
        Log.d("Contrasena", loginViewModel.contrasena)

        when {
            loginViewModel.correo.isBlank() -> {
                tituloAlerta = "Error de validación"
                mensajeAlerta = "Por favor ingrese el usuario."
                textoBotonAlerta = "Cerrar"
                mostrarAlerta = true
            }
            loginViewModel.contrasena.isBlank() -> {
                tituloAlerta = "Error de validación"
                mensajeAlerta = "Por favor ingrese la contraseña."
                textoBotonAlerta = "Cerrar"
                mostrarAlerta = true
            }
            else -> {

                tituloConfirmar = "Confirmación"
                mensajeConfirmar = "¿Está seguro que desea ingresar?"
                textoBtnConfirmar = "Confirmar"
                textoBtnCancelar = "Cancelar"
                mostrarConfirmar = true
            }
        }
    }
    fun confirmarLogin() {
        mostrarConfirmar = false

        if (loginViewModel.correo == "usuario" && loginViewModel.contrasena == "usuario") {
            deberiamosNavegar = true
        } else if (loginViewModel.correo == "admin" && loginViewModel.contrasena == "admin") {
            deberiamosNavegarAdmin = true
        } else {
            tituloAlerta = "Error de credenciales"
            mensajeAlerta = "El correo o la contraseña no corresponden."
            textoBotonAlerta = "Aceptar"
            mostrarAlerta = true
        }
    }
}