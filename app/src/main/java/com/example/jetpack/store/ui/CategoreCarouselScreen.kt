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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    HorizontalPager(
      state = pagerState,
      modifier = Modifier
        .fillMaxWidth()
        .height(350.dp),
      contentPadding = PaddingValues(horizontal = 80.dp),
      pageSpacing = 16.dp
    ) { page ->
      val product = vm.productsCarousel[page]
      val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
      val scale = if (pagerState.currentPage == page) 1f else 0.8f
      val alpha = if (pagerState.currentPage == page) 1f else 0.6f

      Card(
        modifier = Modifier
          .fillMaxHeight()
          .width(240.dp)
          .scale(scale)
          .alpha(alpha)
          .clickable {
            nav.navigate(StoreRoutes.ProductDetails.create(product.id))
          },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
          defaultElevation = if (pagerState.currentPage == page) 16.dp else 8.dp
        )
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
              .height(160.dp)
              .fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
          )
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            product.name,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
          )
          Text(
            "$${product.price}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    Row(
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      repeat(vm.productsCarousel.size) { index ->
        val isSelected = pagerState.currentPage == index
        Box(
          modifier = Modifier
            .padding(4.dp)
            .size(if (isSelected) 12.dp else 8.dp)
            .clip(RoundedCornerShape(50))
            .background(
              if (isSelected) MaterialTheme.colorScheme.primary
              else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
            )
            .clickable {
              scope.launch { pagerState.animateScrollToPage(index) }
            }
        )
      }
    }
  }
}