package com.example.gamezoneapp


import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.gamezoneapp.storage.AppDatabase
import com.example.gamezoneapp.storage.ProductoRepository
import com.example.gamezoneapp.storage.ProductoViewModelFactory
import com.example.gamezoneapp.ui.theme.GameZoneAppTheme
import com.example.gamezoneapp.navigation.AppNavigation
import com.example.gamezoneapp.viewmodel.ProductoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "productos_db"
        ).fallbackToDestructiveMigration()
            .build()
        val productoDao = database.productoDao()
        val carritoDao = database.carritoDao()




        val repository = ProductoRepository(productoDao)
        val factory = ProductoViewModelFactory(repository, carritoDao)
        val productoViewModel = ViewModelProvider(this, factory)[ProductoViewModel::class.java]


        // 4. UI con Compose
        setContent {
            GameZoneAppTheme {
                AppNavigation(productoViewModel)
            }
        }


}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GameZoneAppTheme {
        Greeting("Android")

    }
}
}




