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
import androidx.compose.ui.unit.dp
import com.example.jetpack.store.model.Product
import com.example.jetpack.store.utils.ImageUtils

@Composable
fun ProductCardColumn(
  product: Product,
  onClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp)
      .clickable(onClick = onClick),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    shape = RoundedCornerShape(12.dp)
  ) {
    Row(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      product.imageByteArray?.let { bytes ->
        ImageUtils.byteArrayToImageBitmap(bytes)?.let { bitmap ->
          Image(
            bitmap = bitmap,
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .size(80.dp)
              .clip(RoundedCornerShape(8.dp))
          )
        }
      }

      Spacer(modifier = Modifier.width(16.dp))

      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = product.name,
          style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = product.price,
          style = MaterialTheme.typography.bodyLarge,
          color = MaterialTheme.colorScheme.primary
        )
        if (product.description.isNotBlank()) {
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = product.description,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2
          )
        }
      }
    }
  }
}