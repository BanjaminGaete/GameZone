package com.example.gamezoneapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamezoneapp.models.CarritoItem
import com.example.gamezoneapp.models.Producto
import com.example.gamezoneapp.storage.CarritoDao
import com.example.gamezoneapp.storage.ProductoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductoViewModel(
    private val productoRepository: ProductoRepository,
    private val carritoDao: CarritoDao
) : ViewModel() {

    val productos: StateFlow<List<Producto>> = productoRepository.productos
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    val carrito: StateFlow<List<CarritoItem>> = carritoDao.obtenerCarrito()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun agregarAlCarrito(producto: Producto) {
        viewModelScope.launch {
            val item = CarritoItem(
                productoId = producto.id,
                nombre = producto.nombre,
                precio = producto.precio,
                cantidad = 1
            )
            carritoDao.agregarAlCarrito(item)
        }
    }

    fun vaciarCarrito() {
        viewModelScope.launch {
            carritoDao.vaciarCarrito()
        }
    }

    fun agregarProducto(producto: Producto) {
        viewModelScope.launch {
            productoRepository.agregar(producto)
        }
    }
    fun eliminarProducto(producto: Producto) {
        viewModelScope.launch {
            productoRepository.eliminar(producto)



        }
    }

    fun actualizarProducto(producto: Producto) {
        viewModelScope.launch {
            productoRepository.actualizarProducto(producto)
        }
    }
    fun obtenerProductoPorId(id: Int): Flow<Producto?> {
        return productoRepository.obtenerProductoPorId(id)
    }


}




