package com.example.jetpack.store.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.jetpack.store.database.StoreDatabase
import com.example.jetpack.store.model.Category
import com.example.jetpack.store.model.Product
import com.example.jetpack.store.repository.StoreRepository
import kotlinx.coroutines.launch

class StoreViewModel(application: Application) : AndroidViewModel(application) {

  private val repository: StoreRepository

  val allCategories: LiveData<List<Category>>
  val allProducts: LiveData<List<Product>>

  val favorites = mutableStateListOf<Product>()
  val cart = mutableStateListOf<Product>()

  init {
    val database = StoreDatabase.getDatabase(application)
    repository = StoreRepository(
      database.categoryDao(),
      database.productDao()
    )
    allCategories = repository.allCategories
    allProducts = repository.allProducts
  }

  // Category operations
  fun insertCategory(category: Category) = viewModelScope.launch {
    repository.insertCategory(category)
  }

  fun updateCategory(category: Category) = viewModelScope.launch {
    repository.updateCategory(category)
  }

  fun deleteCategory(category: Category) = viewModelScope.launch {
    repository.deleteCategory(category)
  }

  suspend fun getCategoryById(id: Int): Category? {
    return repository.getCategoryById(id)
  }

  // Product operations
  fun insertProduct(product: Product) = viewModelScope.launch {
    repository.insertProduct(product)
  }

  fun updateProduct(product: Product) = viewModelScope.launch {
    repository.updateProduct(product)
  }

  fun deleteProduct(product: Product) = viewModelScope.launch {
    repository.deleteProduct(product)
  }

  suspend fun getProductById(id: Int): Product? {
    return repository.getProductById(id)
  }

  fun getProductsByCategory(categoryId: Int): LiveData<List<Product>> {
    return repository.getProductsByCategory(categoryId)
  }

  // Favorites and Cart operations
  fun toggleFavorite(product: Product) {
    if (favorites.contains(product)) favorites.remove(product)
    else favorites.add(product)
  }

  fun addToCart(product: Product) {
    cart.add(product)
  }
}