package com.example.jetpack.store.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpack.R

@Composable
fun AdCard() {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(30.dp),
    shape = RoundedCornerShape(16.dp)
  ) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Image(
        painter = painterResource(id = R.drawable.advertisment),
        contentDescription = "Advertisement",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )

      Text(
        text = "Special Offer!",
        color = androidx.compose.ui.graphics.Color.Black,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.End,
        modifier = Modifier.padding(10.dp)
      )
    }
  }
}