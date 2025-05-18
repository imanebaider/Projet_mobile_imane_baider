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


object Routes {
    const val Home = "home"
    const val ProductDetails = "productDetails"
}




@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable(Routes.Home) {
            HomeScreen(onNavigateToDetails = { id ->
                navController.navigate("${Routes.ProductDetails}/$id")
            })
        }

        composable(
            "${Routes.ProductDetails}/{productId}",
            arguments = listOf(navArgument("productId") { type = androidx.navigation.NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(
                productId = backStackEntry.arguments?.getString("productId") ?: "",
                navController = navController
            )
        }
    }
}
















@Composable
fun RüyaFooter() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFF5F5F5))
            .padding(vertical = 32.dp, horizontal = 20.dp)
    ) {
        // Logo & Slogan
        Text(
            text = "Rüya – فناجين",
            style = MaterialTheme.typography.titleLarge,
            fontStyle = FontStyle.Italic,
            color = PinkDark
        )
        Text(
            text = "Chaque tasse est une nouvelle rêverie.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Newsletter
        Text(
            text = "🌸 Ne manquez rien !",
            style = MaterialTheme.typography.titleMedium,
            color = PinkDark
        )
        Text(
            text = "Abonnez-vous à notre newsletter pour découvrir nos nouvelles collections.",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 4.dp)
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Votre e-mail") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Sections (Services / Entreprise / Contact)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Services",
                    style = MaterialTheme.typography.titleSmall,
                    color = PinkDark
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("À propos de nous")
                Text("Livraison")
                Text("Confidentialité")
                Text("Conditions")
            }

            Column {
                Text(
                    text = "Entreprise",
                    style = MaterialTheme.typography.titleSmall,
                    color = PinkDark
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("LARACHE")
                Text("Magasin Rüya")
                Text("0612225554")
                Text("Contact@Rüya.ma")
            }

            Column {
                Text(
                    text = "Réseaux",
                    style = MaterialTheme.typography.titleSmall,
                    color = PinkDark
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("🌸 Pinterest")
                Text("📸 Instagram")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Copyright
        Text(
            text = "© 2025 Rüya – Tous droits réservés.",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
