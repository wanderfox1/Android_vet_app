package com.example.jetpack.store.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpack.store.navigation.StoreRoutes
import com.example.jetpack.store.viewmodel.StoreViewModel

@Composable
fun CategoryColumnScreen(vm: StoreViewModel, nav: NavController) {
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
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
      items(products) { product ->
        ProductCardColumn(product = product) {
          nav.navigate(StoreRoutes.ProductDetails.create(product.id))
        }
      }

      if (products.isEmpty()) {
        item {
          Text(
            "Нет товаров. Добавьте через кнопку +",
            modifier = Modifier
              .fillMaxWidth()
              .padding(32.dp),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
          )
        }
      }
    }
  }
}