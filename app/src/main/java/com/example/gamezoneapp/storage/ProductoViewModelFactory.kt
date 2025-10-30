package com.example.gamezoneapp.storage


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gamezoneapp.viewmodel.ProductoViewModel


class ProductoViewModelFactory(
    private val repository: ProductoRepository,
    private val carritoDao: CarritoDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductoViewModel::class.java)) {
            return ProductoViewModel(repository, carritoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
