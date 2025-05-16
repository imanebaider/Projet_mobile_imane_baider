package com.example.myapplication.nav

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.myapplication.Product
import com.example.myapplication.ProductItem
import com.example.myapplication.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.myapplication.ui.theme.PinkDark
import com.example.myapplication.ui.theme.PinkLight
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer


object Routes {
    const val Home = "home"
    const val ProductDetails = "productDetails"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home) {

        composable(Routes.Home) {
            HomeScreen(onNavigateToDetails = { productId ->
                navController.navigate("${Routes.ProductDetails}/$productId")
            })
        }

        composable(
            "${Routes.ProductDetails}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            DetailsScreen(productId = productId)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(onNavigateToDetails: (String) -> Unit) {
    val products = listOf(
        Product(
            id = "1",
            name = "Fajr",
            price = 19.99,
            imageResId = R.drawable.image1,
            description = "Une tasse inspirée par la sérénité de l’aube, symbole d’un nouveau départ"
        ),
        Product(
            id = "2",
            name = "Noor",
            price = 22.50,
            imageResId = R.drawable.image2,
            description = "Élégante et lumineuse, cette tasse dorée apporte chaleur à chaque instant café"
        ),
        Product(
            id = "3",
            name = "Layali",
            price = 25.00,
            imageResId = R.drawable.image3,
            description = "Un hommage aux nuits orientales, parfaite pour les moments calmes et profonds"
        ),
        Product(
            id = "4",
            name = "Sama",
            price = 21.75,
            imageResId = R.drawable.image4,
            description = "Un design céleste aux nuances bleues, pour s’évader à chaque gorgée"
        ),
        Product(
            id = "5",
            name = "Rimal",
            price = 18.90,
            imageResId = R.drawable.image5,
            description = "Inspirée des dunes dorées, cette tasse incarne la beauté du désert"
        ),
        Product(
            id = "6",
            name = "Andalous",
            price = 29.90,
            imageResId = R.drawable.image6,
            description = "Un style raffiné aux motifs andalous, alliant histoire et élégance"
        ),
        Product(
            id = "7",
            name = "Haneen",
            price = 24.60,
            imageResId = R.drawable.image7,
            description = "Délicate et nostalgique, elle évoque la douceur des souvenirs"
        ),
        Product(
            id = "8",
            name = "Rüya Classique",
            price = 27.00,
            imageResId = R.drawable.image8,
            description = "Le modèle emblématique de Rüya, où l’art rencontre l’authenticité"
        ),
        Product(
            id = "9",
            name = "Asrar",
            price = 23.40,
            imageResId = R.drawable.image9,
            description = "Mystérieuse et artistique, cette tasse séduit les amateurs de beauté singulière"
        ),

    )

    Scaffold(
        containerColor = PinkLight,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Rüya – فناجين حالمة",
                        style = MaterialTheme.typography.titleLarge,
                        color = PinkDark
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = PinkLight
                )
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            item(span = { GridItemSpan(maxLineSpan) }) {
                BannerInfo()
            }
            // بانر
            item(span = { GridItemSpan(maxLineSpan) }) {
                AsyncImage(
                    model = R.drawable.image, // banner doux en rose
                    contentDescription = "Banner Rüya",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .padding(bottom = 16.dp),
                    contentScale = ContentScale.Crop
                )
            }

            // المنتجات
            items(products) { product ->
                ProductItem(
                    product = product,
                    onClick = { onNavigateToDetails(product.id) }
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                RüyaFooter()
            }
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
            text = "Rüya – فناجين حالمة",
            style = MaterialTheme.typography.titleLarge,
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
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    SocialText("🌸 Pinterest")
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    SocialText("📸 Instagram")
                }
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

@Composable
fun SocialText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary
    )
}




@Composable
fun BannerInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFFDEEF8)) // لون وردي فاتح ناعم
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Dépensez 600Dhs pour la livraison gratuite",
            style = MaterialTheme.typography.titleMedium,
            color = PinkDark
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "22 000 abonnés", style = MaterialTheme.typography.bodyMedium)
            Text(text = "120 abonnés", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Buvez votre café avec de jolies tasses",
            style = MaterialTheme.typography.titleSmall,
            color = PinkDark
        )
        Text(
            text = "Découvrez les mugs",
            style = MaterialTheme.typography.bodyMedium,
            color = PinkDark,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun DetailsScreen(productId: String) {
    Text("Details of product ID: $productId")
}


