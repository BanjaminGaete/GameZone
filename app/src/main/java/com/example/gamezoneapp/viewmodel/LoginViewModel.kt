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

    fun descartarAlerta(){
        mostrarAlerta = false
    }

    var deberiamosNavegar by mutableStateOf(false)
        private set

    fun cambiarEstadoNavegacion(){
        deberiamosNavegar = false
    }
    var deberiamosNavegarAdmin by mutableStateOf(false)
        private set

    fun cambiarEstadoNavegacionAdmin(){
        deberiamosNavegarAdmin = false
    }

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

    fun btnAceptarConfirmar(){
        mostrarConfirmar = false
    }
    fun btnCancelarConfirmar(){
        mostrarConfirmar = false
    }
    fun terminarConfirmar(){
        mostrarConfirmar = false
    }

    fun auth(){
        Log.d("Correo", loginViewModel.correo)
        Log.d("Contrasena", loginViewModel.contrasena)

        if(loginViewModel.correo == "usuario" && loginViewModel.contrasena == "usuario"){
            //navegar al inicio usuario
            deberiamosNavegar = true
        }else if(loginViewModel.correo == "admin" && loginViewModel.contrasena == "admin"){
            //navegar al inicio admin
            deberiamosNavegarAdmin = true
        }else if(loginViewModel.correo.isBlank() || loginViewModel.contrasena.isBlank()){
            //Error ---> alerta
            tituloAlerta = "Error de validación"
            mensajeAlerta = "El correo y la contraseña no pueden estar vacíos."
            textoBotonAlerta = "Confrimar"
            //mostrarAlerta = true

            tituloConfirmar = "Confirmación"
            mensajeConfirmar = "Esta seguro que desea hacerlo?"
            textoBtnConfirmar = "Confirmar"
            textoBtnCancelar = "Cancelar"
            mostrarConfirmar = true
        }else{
            //Error  --> alerta
            tituloAlerta = "Error de credenciales"
            mensajeAlerta = "El correo o la contraseña no corresponden."
            textoBotonAlerta = "Aceptar"
            mostrarAlerta = true
        }
    }
}