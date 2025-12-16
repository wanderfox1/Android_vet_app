package com.example.jetpack.store.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpack.store.model.Category
import com.example.jetpack.store.model.Product

@Database(
  entities = [Category::class, Product::class],
  version = 1,
  exportSchema = false
)
abstract class StoreDatabase : RoomDatabase() {

  abstract fun categoryDao(): CategoryDao
  abstract fun productDao(): ProductDao

  companion object {
    @Volatile
    private var INSTANCE: StoreDatabase? = null

    fun getDatabase(context: Context): StoreDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          StoreDatabase::class.java,
          "store_database"
        )
          .fallbackToDestructiveMigration()
          .build()
        INSTANCE = instance
        instance
      }
    }
  }
}