package com.example.jetpack.store.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpack.store.navigation.StoreRoutes
import com.example.jetpack.store.viewmodel.StoreViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryGridScreen(vm: StoreViewModel, nav: NavController) {
  val products by vm.allProducts.observeAsState(emptyList())

  Scaffold(
    floatingActionButton = {
      FloatingActionButton(
        onClick = { nav.navigate(StoreRoutes.AddProduct.route) },
        containerColor = MaterialTheme.colorScheme.primary
      ) {
        Icon(
          Icons.Default.Add,
          contentDescription = "Добавить товар"
        )
      }
    }
  ) { paddingValues ->
    if (products.isEmpty()) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(paddingValues),
        contentAlignment = Alignment.Center
      ) {
        Text(
          "Нет товаров. Добавьте через кнопку +",
          style = MaterialTheme.typography.bodyLarge
        )
      }
    } else {
      LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(paddingValues)
      ) {
        items(products) { product ->
          ProductCardGrid(product = product) {
            nav.navigate(StoreRoutes.ProductDetails.create(product.id))
          }
        }
      }
    }
  }
}