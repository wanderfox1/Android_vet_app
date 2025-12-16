package com.example.jetpack.store.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jetpack.store.model.Category

@Dao
interface CategoryDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(category: Category): Long

  @Update
  suspend fun update(category: Category)

  @Delete
  suspend fun delete(category: Category)

  @Query("SELECT * FROM categories ORDER BY name ASC")
  fun getAllCategories(): LiveData<List<Category>>

  // ADD THIS METHOD - needed for DatabaseInitializer
  @Query("SELECT * FROM categories ORDER BY name ASC")
  suspend fun getAllCategoriesForInit(): List<Category>

  @Query("SELECT * FROM categories WHERE id = :categoryId")
  suspend fun getCategoryById(categoryId: Int): Category?

  @Query("DELETE FROM categories")
  suspend fun deleteAll()
}