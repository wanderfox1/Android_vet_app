package com.example.jetpack.store.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.jetpack.store.navigation.StoreNavGraph
import com.example.jetpack.store.navigation.StoreRoutes
import com.example.jetpack.store.viewmodel.StoreViewModel

@Composable
fun StoreScreen(vm: StoreViewModel) {
  val navController = rememberNavController()
  var selectedTab by remember { mutableIntStateOf(0) }
  val tabs = listOf("Column", "Grid", "Carousel")

  val tabRoutes = listOf(
    StoreRoutes.CategoryColumn.route,
    StoreRoutes.CategoryGrid.route,
    StoreRoutes.CategoryCarousel.route
  )

  LaunchedEffect(selectedTab) {
    navController.navigate(tabRoutes[selectedTab]) {
      popUpTo(StoreRoutes.CategoryColumn.route) { inclusive = false }
      launchSingleTop = true
    }
  }

  Scaffold(
    topBar = { StoreTopBar() },
    bottomBar = { StoreBottomBar(vm) }
  ) { padding ->
    Column(modifier = Modifier.padding(padding)) {
      TabRow(selectedTabIndex = selectedTab) {
        tabs.forEachIndexed { index, title ->
          Tab(
            text = { Text(title) },
            selected = selectedTab == index,
            onClick = { selectedTab = index }
          )
        }
      }

      Box(modifier = Modifier.fillMaxSize()) {
        StoreNavGraph(
          navController = navController,
          vm = vm,
          startDestination = tabRoutes[selectedTab]
        )
      }
    }
  }
}