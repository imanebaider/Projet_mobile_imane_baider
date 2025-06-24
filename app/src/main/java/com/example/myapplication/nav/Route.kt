package com.example.myapplication.nav
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.product.ProductViewModel
import com.example.myapplication.ui.product.details.DetailsScreen
import com.example.myapplication.ui.product.screens.HomeScreen
import com.example.myapplication.ui.product.screens.MonPanierScreen
import com.example.myapplication.ui.product.screens.ValidationCommandeScreen
import com.example.myapplication.ui.product.screens.PaymentScreen // <-- ضروري

object Routes {
    const val Home = "home"
    const val ProductDetails = "productDetails"
    const val Cart = "cart"
    const val Validation = "validation" // 🆕
    const val Payment = "payment"

}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: ProductViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.Home) {
        composable(Routes.Home) {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToDetails = { id -> navController.navigate("${Routes.ProductDetails}/$id") },
                onNavigateToCart = { navController.navigate(Routes.Cart) },
                onNavigateToProfile = { /* navController.navigate("profile") */ },
                onNavigateToOrders = { /* navController.navigate("orders") */ },
                onNavigateToFavorites = { /* navController.navigate("favorites") */ },
                onNavigateToLogin = { /* navController.navigate("login") */ }
            )
        }

        composable(
            "${Routes.ProductDetails}/{productId}",
            arguments = listOf(navArgument("productId") {
                type = NavType.StringType
            })

        ) { backStackEntry ->
            DetailsScreen(
                productId = backStackEntry.arguments?.getString("productId") ?: "",
                navController = navController,
                viewModel = viewModel
            )
        }


        composable(Routes.Cart) {
            MonPanierScreen(
                onBack = { navController.popBackStack() },
                onProductClick = { productId ->
                    navController.navigate("${Routes.ProductDetails}/$productId")
                },
                onPasserCommande = {
                    navController.navigate(Routes.Validation)
                }

            )
        }
        composable(Routes.Validation) {
            ValidationCommandeScreen(
                onBack = { navController.popBackStack() },
                onConfirmPayment = { clientInfo, paymentMethod ->
                    navController.navigate(Routes.Payment)
                }
            )
        }

        composable(Routes.Payment) {
            PaymentScreen(onBack = { navController.popBackStack() })
        }



    }
}

