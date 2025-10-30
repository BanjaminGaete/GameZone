package com.example.gamezoneapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamezoneapp.models.Producto
import com.example.gamezoneapp.storage.ProductoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductoViewModel(private val repository: ProductoRepository) : ViewModel() {
    val productos: StateFlow<List<Producto>> = repository.productos.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun agregarProductoDemo() {
        viewModelScope.launch {
            val demo = Producto(
                nombre = "Juego demo",
                descripcion = "Producto de prueba",
                precio = 0.0,
                imagen = "https://via.placeholder.com/150"
            )
            repository.agregar(demo)
        }
    }

    fun editarProducto(producto: Producto) {
        viewModelScope.launch {
            val editado = producto.copy(nombre = producto.nombre + " (editado)")
            repository.editar(editado)
        }
    }

    fun eliminarProducto(producto: Producto) {
        viewModelScope.launch {
            repository.eliminar(producto)
        }
    }
}
