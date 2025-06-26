package com.example.myapplication.nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.myapplication.ui.product.ProductViewModel
import com.example.myapplication.ui.product.details.DetailsScreen
import com.example.myapplication.ui.product.screens.*


object Routes {
    const val Home = "home"
    const val ProductDetails = "productDetails"
    const val Cart = "cart"
    const val Validation = "validation"
    const val Payment = "payment"
    const val Orders = "orders"
    const val Auth = "auth"
    const val SignUp = "signup"
    const val Profile = "profile"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: ProductViewModel = viewModel()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Routes.Home) {

        composable(Routes.Home) {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToDetails = { id -> navController.navigate("${Routes.ProductDetails}/$id") },
                onNavigateToCart = { navController.navigate(Routes.Cart) },
                onNavigateToProfile = { navController.navigate(Routes.Profile) },  // هنا كملنا الفاصلة
                onNavigateToOrders = { navController.navigate(Routes.Orders) },
                onNavigateToFavorites = { navController.navigate("favorites") },
                onNavigateToLogin = { navController.navigate(Routes.Auth) }
            )

        }

        composable(
            "${Routes.ProductDetails}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
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
                onConfirmPayment = { _, _ ->
                    navController.navigate(Routes.Payment)
                }
            )
        }

        composable(Routes.Payment) {
            PaymentScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.Orders) {
            MesCommandesScreen(
                onBack = { navController.popBackStack() },
                onProductClick = { productId ->
                    navController.navigate("${Routes.ProductDetails}/$productId")
                }
            )
        }

        composable("favorites") {
            FavoritesScreen(
                onNavigateToDetails = { productId ->
                    navController.navigate("${Routes.ProductDetails}/$productId")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.Auth) {
            AuthScreen(
                context = context,
                onLoginSuccess = {
                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.Auth) { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Routes.SignUp)
                }
            )
        }

        composable(Routes.SignUp) {
            SignUpScreen(
                context = context,
                onSignUpSuccess = {
                    navController.navigate(Routes.Auth) {
                        popUpTo(Routes.SignUp) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.Profile) {
            ProfileScreen(
                context = context,
                onLogout = {
                    navController.navigate(Routes.Auth) {
                        popUpTo(Routes.Home) { inclusive = true }
                    }
                }
            )
        }


    }
}