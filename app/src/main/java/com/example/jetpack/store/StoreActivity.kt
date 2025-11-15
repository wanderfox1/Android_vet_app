package com.example.jetpack.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jetpack.store.ui.StoreScreen
import com.example.jetpack.store.viewmodel.StoreViewModel

class StoreActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val vm = StoreViewModel()
    setContent { StoreScreen(vm = vm) }
  }
}