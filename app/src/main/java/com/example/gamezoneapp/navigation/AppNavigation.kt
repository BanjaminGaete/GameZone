package com.example.gamezoneapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.gamezoneapp.viewmodel.ProductoViewModel



@Composable
fun AppNavigation(productoViewModel: ProductoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        // Aquí irán tus pantallas
    }
}