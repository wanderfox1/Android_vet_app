package com.example.jetpack.store.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jetpack.store.model.Product

@Dao
interface ProductDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(product: Product): Long

  @Update
  suspend fun update(product: Product)

  @Delete
  suspend fun delete(product: Product)

  @Query("SELECT * FROM products ORDER BY name ASC")
  fun getAllProducts(): LiveData<List<Product>>

  @Query("SELECT * FROM products WHERE id = :productId")
  suspend fun getProductById(productId: Int): Product?

  @Query("SELECT * FROM products WHERE categoryId = :categoryId ORDER BY name ASC")
  fun getProductsByCategory(categoryId: Int): LiveData<List<Product>>

  @Query("DELETE FROM products")
  suspend fun deleteAll()
}
