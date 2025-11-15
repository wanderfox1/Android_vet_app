package com.example.jetpack.store.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetpack.R
import com.example.jetpack.store.model.Product

class StoreViewModel : ViewModel() {

  val productsColumn = List(6) {
    Product(
      id = it,
      name = "Product $it",
      price = "$${10 + it}",
      image = R.drawable.pet_secondary,
      description = "Description for product $it"
    )
  }

  val productsGrid = List(6) {
    Product(
      id = 100 + it,
      name = "Grid Product $it",
      price = "$${20 + it}",
      image = R.drawable.pet_secondary,
      description = "Description for grid product $it"
    )
  }

  val productsCarousel = List(6) {
    Product(
      id = 200 + it,
      name = "Carousel Product $it",
      price = "$${30 + it}",
      image = R.drawable.pet_secondary,
      description = "Description for carousel product $it"
    )
  }

  val favorites = mutableStateListOf<Product>()
  val cart = mutableStateListOf<Product>()

  private val allProducts = productsColumn + productsGrid + productsCarousel

  fun getProduct(id: Int): Product? {
    return allProducts.find { it.id == id }
  }

  fun toggleFavorite(product: Product) {
    if (favorites.contains(product)) favorites.remove(product)
    else favorites.add(product)
  }

  fun addToCart(product: Product) {
    cart.add(product)
  }
}
