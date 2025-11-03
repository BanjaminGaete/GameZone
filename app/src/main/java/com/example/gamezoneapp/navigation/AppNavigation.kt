package com.example.gamezoneapp.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.gamezoneapp.viewmodel.ProductoViewModel
import com.example.gamezoneapp.viewmodel.PerfilViewModel
import com.example.gamezoneapp.views.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(productoViewModel: ProductoViewModel) {
    val navController = rememberNavController()
    val perfilViewModel: PerfilViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, productoViewModel) }
        composable("admin") { AdminPanelScreen(navController, productoViewModel) }
        composable("inicio") { InicioScreen(navController, productoViewModel) }
        composable("carrito") { CarritoScreen(navController, productoViewModel) }
        composable("agregar") { AgregarProductoScreen(navController, productoViewModel) }
        composable("editar/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (id != null) {
                EditarProductoScreen(id, productoViewModel, navController)
            }
        }
        composable("perfil") { PerfilScreen(navController, perfilViewModel) }
        composable("camara") { CamaraScreen(navController) }
        composable("historial") { HistorialScreen(navController) }
    }
}
