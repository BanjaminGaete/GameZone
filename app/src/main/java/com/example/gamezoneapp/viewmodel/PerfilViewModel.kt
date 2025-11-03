package com.example.gamezoneapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PerfilViewModel : ViewModel() {


    private val _nombre = MutableStateFlow("")
    private val _direccion = MutableStateFlow("")
    private val _contacto = MutableStateFlow("")

    val nombre: StateFlow<String> = _nombre
    val direccion: StateFlow<String> = _direccion
    val contacto: StateFlow<String> = _contacto

    fun cambioNombre(n: String) {
        _nombre.value = n
    }

    fun cambioDireccion(d: String) {
        _direccion.value = d
    }

    fun cambioContacto(c: String) {
        _contacto.value = c
    }


    var mostrarAlerta = MutableStateFlow(false)
        private set
    var tituloAlerta = MutableStateFlow("")
        private set
    var mensajeAlerta = MutableStateFlow("")
        private set
    var textoBotonAlerta = MutableStateFlow("")
        private set

    fun descartarAlerta() {
        mostrarAlerta.value = false
    }

    var mostrarConfirmar = MutableStateFlow(false)
        private set
    var tituloConfirmar = MutableStateFlow("")
        private set
    var mensajeConfirmar = MutableStateFlow("")
        private set
    var textoBtnConfirmar = MutableStateFlow("")
        private set
    var textoBtnCancelar = MutableStateFlow("")
        private set

    fun cancelarConfirmacion() {
        mostrarConfirmar.value = false
    }


    var datosGuardados = MutableStateFlow(false)
        private set

    fun resetearResumen() {
        datosGuardados.value = false
    }


    fun validarDatos() {
        when {
            _nombre.value.isBlank() -> {
                tituloAlerta.value = "Falta el nombre"
                mensajeAlerta.value = "Por favor ingresa tu nombre."
                textoBotonAlerta.value = "Cerrar"
                mostrarAlerta.value = true
            }
            _direccion.value.isBlank() -> {
                tituloAlerta.value = "Falta la dirección"
                mensajeAlerta.value = "Por favor ingresa tu dirección."
                textoBotonAlerta.value = "Cerrar"
                mostrarAlerta.value = true
            }
            _contacto.value.isBlank() -> {
                tituloAlerta.value = "Falta el contacto"
                mensajeAlerta.value = "Por favor ingresa tu número contacto."
                textoBotonAlerta.value = "Cerrar"
                mostrarAlerta.value = true
            }
            else -> {
                tituloConfirmar.value = "Confirmar datos"
                mensajeConfirmar.value = "¿Estás seguro que deseas guardar esta información?"
                textoBtnConfirmar.value = "Guardar"
                textoBtnCancelar.value = "Cancelar"
                mostrarConfirmar.value = true
            }
        }
    }


    fun confirmarGuardado() {
        mostrarConfirmar.value = false
        datosGuardados.value = true
    }
}
