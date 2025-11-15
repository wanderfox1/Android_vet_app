package com.example.jetpack.store.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.jetpack.store.navigation.StoreRoutes
import com.example.jetpack.store.viewmodel.StoreViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryGridScreen(vm: StoreViewModel, nav: NavController) {
  LazyVerticalGrid(columns = GridCells.Fixed(2)) {
    items(vm.productsGrid.size) { index ->
      val item = vm.productsGrid[index]
      ProductCardGrid(product = item) {
        nav.navigate(StoreRoutes.ProductDetails.create(item.id))
      }
    }
  }
}
