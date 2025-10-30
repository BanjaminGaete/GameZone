package com.example.gamezoneapp.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gamezoneapp.models.CarritoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CarritoDao {
    @Query("SELECT * FROM carrito")
    fun obtenerCarrito(): Flow<List<CarritoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregar(item: CarritoItem)

    @Delete
    suspend fun eliminar(item: CarritoItem)
}
