package com.example.jetpack.store.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpack.store.viewmodel.StoreViewModel
import com.example.jetpack.store.utils.ImageUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(vm: StoreViewModel, id: Int, nav: NavController) {
  var product by remember { mutableStateOf<com.example.jetpack.store.model.Product?>(null) }
  val scope = rememberCoroutineScope()

  LaunchedEffect(id) {
    scope.launch {
      product = vm.getProductById(id)
    }
  }

  if (product == null) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
      CircularProgressIndicator()
    }
    return
  }

  val currentProduct = product!!

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(currentProduct.name) },
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
      currentProduct.imageByteArray?.let { bytes ->
        ImageUtils.byteArrayToImageBitmap(bytes)?.let { bitmap ->
          Image(
            bitmap = bitmap,
            contentDescription = currentProduct.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .fillMaxWidth()
              .height(280.dp)
          )
        }
      }

      Spacer(Modifier.height(16.dp))

      Text(currentProduct.name, style = MaterialTheme.typography.headlineSmall)
      Text(currentProduct.price, style = MaterialTheme.typography.bodyLarge)

      Spacer(Modifier.height(16.dp))

      Text(currentProduct.description)

      Spacer(Modifier.height(32.dp))

      Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Button(
          onClick = { vm.addToCart(currentProduct) },
          modifier = Modifier.weight(1f)
        ) {
          Text("Add to cart")
        }

        Button(
          onClick = { vm.toggleFavorite(currentProduct) },
          modifier = Modifier.weight(1f)
        ) {
          Text("Fav")
        }
      }
    }
  }
}