package com.example.gamezoneapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CarritoItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productoId: Int,
    val nombre: String,
    val precio: Double,
    val cantidad: Int
)
