package com.example.jetpack.store.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpack.store.viewmodel.StoreViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(vm: StoreViewModel, id: Int, nav: NavController) {
  val product = vm.getProduct(id) ?: return

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(product.name) },
        navigationIcon = {
          IconButton(onClick = { nav.popBackStack() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
          }
        }
      )
    }
  ) { pad ->
    Column(
      modifier = Modifier
        .padding(pad)
        .padding(16.dp)
        .fillMaxSize()
    ) {
      Image(
        painter = painterResource(product.image),
        contentDescription = product.name,
        modifier = Modifier
          .fillMaxWidth()
          .height(280.dp)
      )

      Spacer(Modifier.height(16.dp))

      Text(product.name, style = MaterialTheme.typography.headlineSmall)
      Text("${product.price}", style = MaterialTheme.typography.bodyLarge)

      Spacer(Modifier.height(16.dp))

      Text(product.description)

      Spacer(Modifier.height(32.dp))

      Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Button(onClick = { vm.addToCart(product) }, modifier = Modifier.weight(1f)) {
          Text("Add to cart")
        }

        Button(onClick = { vm.toggleFavorite(product) }, modifier = Modifier.weight(1f)) {
          Text("Fav")
        }
      }
    }
  }
}
