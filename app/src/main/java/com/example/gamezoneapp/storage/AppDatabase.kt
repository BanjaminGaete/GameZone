package com.example.gamezoneapp.storage


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gamezoneapp.models.Producto
import com.example.gamezoneapp.models.CarritoItem

@Database(entities = [Producto::class, CarritoItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao
    abstract fun carritoDao(): CarritoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gamezonapp_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
