package com.example.jetpack.store.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpack.store.model.Product
import com.example.jetpack.store.navigation.StoreRoutes
import com.example.jetpack.store.viewmodel.StoreViewModel

@Composable
fun CategoryColumnScreen(vm: StoreViewModel, nav: NavController) {
  LazyColumn {
    itemsIndexed(vm.productsColumn) { index, item ->
      if ((index + 1) % 5 == 0) {
        AdCard() // just call it directly
      }

      ProductCardColumn(product = item) {
        nav.navigate(StoreRoutes.ProductDetails.create(item.id))
      }
    }

    item {
      Button(
        onClick = { nav.navigate(StoreRoutes.CategoryCarousel.route) },
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        Text("Go to Carousel")
      }
    }
  }
}