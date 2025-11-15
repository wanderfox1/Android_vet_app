package com.example.jetpack.store.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetpack.store.viewmodel.StoreViewModel

@Composable
fun StoreBottomBar(vm: StoreViewModel) {
  BottomAppBar {
    IconButton(onClick = {}) {
      Icon(Icons.Default.Home, null)
    }
    BadgedBox(badge = { if (vm.favorites.isNotEmpty()) Badge { Text("${vm.favorites.size}") } }) {
      IconButton(onClick = {}) {
        Icon(Icons.Default.Favorite, null)
      }
    }
    BadgedBox(badge = { if (vm.cart.isNotEmpty()) Badge { Text("${vm.cart.size}") } }) {
      IconButton(onClick = {}) {
        Icon(Icons.Default.ShoppingCart, null)
      }
    }
  }
}
