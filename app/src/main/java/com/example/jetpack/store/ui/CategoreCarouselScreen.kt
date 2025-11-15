package com.example.jetpack.store.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.jetpack.store.viewmodel.StoreViewModel
import com.example.jetpack.store.navigation.StoreRoutes
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryCarouselScreen(vm: StoreViewModel, nav: NavController) {
  val pagerState = rememberPagerState(pageCount = { vm.productsCarousel.size })
  val scope = rememberCoroutineScope()

  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    HorizontalPager(
      state = pagerState,
      modifier = Modifier
        .fillMaxWidth()
        .height(320.dp),
      contentPadding = PaddingValues(horizontal = 48.dp),
      pageSpacing = 16.dp
    ) { page ->
      val product = vm.productsCarousel[page]
      val scale = if (pagerState.currentPage == page) 1f else 0.85f

      Card(
        modifier = Modifier
          .fillMaxHeight()
          .width(220.dp)
          .scale(scale)
          .clickable {
            nav.navigate(StoreRoutes.ProductDetails.create(product.id))
          },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
      ) {
        Column(
          modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          Image(
            painter = painterResource(product.image),
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .height(140.dp)
              .fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
          )
          Spacer(modifier = Modifier.height(12.dp))
          Text(product.name, style = MaterialTheme.typography.titleMedium)
          Text("${product.price}", style = MaterialTheme.typography.bodyMedium)
          Spacer(modifier = Modifier.height(8.dp))
          Button(onClick = {
            nav.navigate(StoreRoutes.ProductDetails.create(product.id))
          }) {
            Text("Details")
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Page indicator dots
    Row(
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      repeat(vm.productsCarousel.size) { index ->
        val isSelected = pagerState.currentPage == index
        Box(
          modifier = Modifier
            .padding(4.dp)
            .size(if (isSelected) 10.dp else 6.dp)
            .clip(RoundedCornerShape(50))
            .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray)
            .clickable {
              scope.launch { pagerState.scrollToPage(index) }
            }
        )
      }
    }
  }
}
