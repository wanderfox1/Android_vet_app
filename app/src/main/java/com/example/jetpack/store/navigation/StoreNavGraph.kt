package com.example.jetpack.store.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpack.store.ui.*
import com.example.jetpack.store.viewmodel.StoreViewModel

sealed class StoreRoutes(val route: String) {
  object CategoryColumn : StoreRoutes("column")
  object CategoryGrid : StoreRoutes("grid")
  object CategoryCarousel : StoreRoutes("carousel")
  object ProductDetails : StoreRoutes("details/{id}") {
    fun create(id: Int) = "details/$id"
  }
  object AddProduct : StoreRoutes("add_product")
  object AddCategory : StoreRoutes("add_category")
}

@Composable
fun StoreNavGraph(
  navController: NavHostController,
  vm: StoreViewModel,
  startDestination: String
) {
  NavHost(
    navController = navController,
    startDestination = startDestination
  ) {

    composable(StoreRoutes.CategoryColumn.route) {
      CategoryColumnScreen(vm = vm, nav = navController)
    }

    composable(StoreRoutes.CategoryGrid.route) {
      CategoryGridScreen(vm = vm, nav = navController)
    }

    composable(StoreRoutes.CategoryCarousel.route) {
      CategoryCarouselScreen(vm = vm, nav = navController)
    }

    composable(StoreRoutes.ProductDetails.route) { backStackEntry ->
      val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
      if (id != null) {
        ProductDetailsScreen(vm = vm, id = id, nav = navController)
      }
    }

    composable(StoreRoutes.AddProduct.route) {
      AddProductScreen(vm = vm, nav = navController)
    }

    composable(StoreRoutes.AddCategory.route) {
      AddCategoryScreen(vm = vm, nav = navController)
    }
  }
}