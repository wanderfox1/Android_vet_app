package com.example.jetpack.store.repository

import androidx.lifecycle.LiveData
import com.example.jetpack.store.database.CategoryDao
import com.example.jetpack.store.database.ProductDao
import com.example.jetpack.store.model.Category
import com.example.jetpack.store.model.Product

class StoreRepository(
  private val categoryDao: CategoryDao,
  private val productDao: ProductDao
) {

  val allCategories: LiveData<List<Category>> = categoryDao.getAllCategories()

  suspend fun insertCategory(category: Category): Long {
    return categoryDao.insert(category)
  }

  suspend fun updateCategory(category: Category) {
    categoryDao.update(category)
  }

  suspend fun deleteCategory(category: Category) {
    categoryDao.delete(category)
  }

  suspend fun getCategoryById(id: Int): Category? {
    return categoryDao.getCategoryById(id)
  }

  val allProducts: LiveData<List<Product>> = productDao.getAllProducts()

  suspend fun insertProduct(product: Product): Long {
    return productDao.insert(product)
  }

  suspend fun updateProduct(product: Product) {
    productDao.update(product)
  }

  suspend fun deleteProduct(product: Product) {
    productDao.delete(product)
  }

  suspend fun getProductById(id: Int): Product? {
    return productDao.getProductById(id)
  }

  fun getProductsByCategory(categoryId: Int): LiveData<List<Product>> {
    return productDao.getProductsByCategory(categoryId)
  }
}