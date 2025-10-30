package com.example.gamezoneapp.storage

import android.content.Context
import androidx.room.Room
import com.example.gamezoneapp.models.Producto
import kotlinx.coroutines.flow.Flow




class ProductoRepository(private val dao: ProductoDao) {
    val productos: Flow<List<Producto>> = dao.obtenerTodos()

    suspend fun agregar(producto: Producto) = dao.agregar(producto)
    suspend fun eliminar(producto: Producto) = dao.eliminar(producto)

    suspend fun actualizarProducto(producto: Producto) {
        dao.actualizarProducto(producto)
    }

    fun obtenerProductoPorId(id: Int): Flow<Producto?> {
        return dao.obtenerPorId(id)
    }
}


