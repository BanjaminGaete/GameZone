package com.example.gamezoneapp.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gamezoneapp.models.CarritoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CarritoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarAlCarrito(item: CarritoItem)

    @Query("SELECT * FROM CarritoItem")
    fun obtenerCarrito(): Flow<List<CarritoItem>>

    @Update
    suspend fun actualizarCantidad(item: CarritoItem)

    @Query("DELETE FROM CarritoItem")
    suspend fun vaciarCarrito()

    @Delete
    suspend fun eliminarItem(item: CarritoItem)
}
