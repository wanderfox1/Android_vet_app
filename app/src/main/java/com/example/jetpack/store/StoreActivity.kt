package com.example.jetpack.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack.store.database.DatabaseInitializer
import com.example.jetpack.store.database.StoreDatabase
import com.example.jetpack.store.ui.StoreScreen
import com.example.jetpack.store.viewmodel.StoreViewModel

class StoreActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Initialize database with test data on first launch
    val database = StoreDatabase.getDatabase(applicationContext)
    DatabaseInitializer.initialize(applicationContext, database)

    // Use ViewModelProvider to get the AndroidViewModel
    val vm = ViewModelProvider(this)[StoreViewModel::class.java]

    setContent {
      StoreScreen(vm = vm)
    }
  }
}