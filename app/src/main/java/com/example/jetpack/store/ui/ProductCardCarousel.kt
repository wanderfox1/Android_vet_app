package com.example.jetpack.store.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpack.store.model.Product
import com.example.jetpack.store.utils.ImageUtils

@Composable
fun ProductCardCarousel(product: Product, onClick: () -> Unit) {
  Card(
    modifier = Modifier
      .size(250.dp)
      .clickable { onClick() },
    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    shape = RoundedCornerShape(16.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(12.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Display image from database ByteArray
      product.imageByteArray?.let { bytes ->
        ImageUtils.byteArrayToImageBitmap(bytes)?.let { bitmap ->
          Image(
            bitmap = bitmap,
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .size(180.dp)
              .clip(RoundedCornerShape(12.dp))
          )
        }
      } ?: run {
        // Placeholder when no image
        Box(
          modifier = Modifier
            .size(180.dp)
            .clip(RoundedCornerShape(12.dp)),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "No Image",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = product.name,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        maxLines = 2
      )

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = product.price,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary
      )
    }
  }
}