package com.example.gamezoneapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamezoneapp.viewmodel.ProductoViewModel
import com.example.gamezoneapp.views.AgregarProductoScreen
import com.example.gamezoneapp.views.CarritoScreen
import com.example.gamezoneapp.views.InicioScreen
import com.example.gamezoneapp.views.LoginScreen
import com.example.gamezoneapp.views.AdminPanelScreen
import com.example.gamezoneapp.views.EditarProductoScreen

@Composable
fun AppNavigation(productoViewModel: ProductoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, productoViewModel) }
        composable("admin") {
            AdminPanelScreen(navController = navController, productoViewModel = productoViewModel)
        }
        composable("inicio") {
            InicioScreen(navController = navController, productoViewModel = productoViewModel)
        }

        composable("carrito") {
            CarritoScreen(navController = navController, productoViewModel = productoViewModel)
        }
        composable("agregar") {
            AgregarProductoScreen(navController = navController, productoViewModel = productoViewModel)
        }

        composable("editar/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (id != null) {
                EditarProductoScreen(
                    productoId = id,
                    productoViewModel = productoViewModel,
                    navController = navController
                )
    }
        }
    }
}



