package com.example.gamezoneapp.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gamezoneapp.models.Producto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {
    @Query("SELECT * FROM productos")
    fun obtenerTodos(): Flow<List<Producto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregar(producto: Producto)

    @Update
    suspend fun editar(producto: Producto)

    @Delete
    suspend fun eliminar(producto: Producto)

    @Update
    suspend fun actualizarProducto(producto: Producto)

    @Query("SELECT * FROM productos WHERE id = :id")
    fun obtenerPorId(id: Int): Flow<Producto?>

}
