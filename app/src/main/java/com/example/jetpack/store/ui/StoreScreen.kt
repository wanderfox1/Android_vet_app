package com.example.jetpack.store.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.jetpack.store.navigation.StoreNavGraph
import com.example.jetpack.store.viewmodel.StoreViewModel

@Composable
fun StoreScreen(vm: StoreViewModel) {
  val navController = rememberNavController()

  Scaffold(
    topBar = { StoreTopBar() },
    bottomBar = { StoreBottomBar(vm) }
  ) { padding ->
    Box(modifier = Modifier.padding(padding)) {
      StoreNavGraph(navController = navController, vm = vm)
    }
  }
}