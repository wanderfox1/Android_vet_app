package com.example.jetpack.store.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpack.R

@Composable
fun AdCard() {
  Card(
    modifier = Modifier
      .width(180.dp)
      .height(180.dp),
    shape = RoundedCornerShape(16.dp)
  ) {
    Image(
      painter = painterResource(id = R.drawable.advertisment),
      contentDescription = "Advertisement",
      contentScale = ContentScale.Crop,
      modifier = Modifier.fillMaxSize()
    )
  }
}
