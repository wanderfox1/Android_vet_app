package com.example.jetpack.store.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpack.store.model.Product

@Composable
fun ProductCardGrid(product: Product, onClick: () -> Unit) {
  Card(modifier = Modifier.clickable { onClick() }) {
    Column {
      Image(painterResource(product.image), null, modifier = Modifier.size(100.dp))
      Text(product.name)
      Text(product.price)
    }
  }
}
