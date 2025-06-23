package com.example.myapplication.nav
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import com.example.myapplication.ui.theme.PinkDark
import com.example.emtyapp.ui.product.details.DetailsScreen
import com.example.myapplication.ui.product.screens.HomeScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.product.ProductViewModel


object Routes {
    const val Home = "home"
    const val ProductDetails = "productDetails"
}


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // إنشاء ViewModel مشترك
    val viewModel: ProductViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable(Routes.Home) {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToDetails = { id ->
                    navController.navigate("${Routes.ProductDetails}/$id")
                }
            )
        }

        composable(
            "${Routes.ProductDetails}/{productId}",
            arguments = listOf(navArgument("productId") {
                type = androidx.navigation.NavType.StringType
            })
        ) { backStackEntry ->
            DetailsScreen(
                productId = backStackEntry.arguments?.getString("productId") ?: "",
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}















