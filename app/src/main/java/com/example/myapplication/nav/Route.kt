package com.example.myapplication.nav

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.myapplication.ui.theme.PinkDark
import com.example.myapplication.ui.theme.PinkLight

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf
import androidx.navigation.NavController


object Routes {
    const val Home = "home"
    const val ProductDetails = "productDetails"
}

// 1. products معرف خارج أي كومبوزابل (global)
val products = listOf(
    Product(
        id = "1",
        name = "Fajr",
        price = 19.99,
        imageResId = R.drawable.image1,
        description = "Une tasse inspirée par la sérénité de l’aube, symbole d’un nouveau départ" ,
        reviews = 5.0

    ),
    Product(
        id = "2",
        name = "Noor",
        price = 22.50,
        imageResId = R.drawable.image2,
        description = "Élégante et lumineuse, cette tasse dorée apporte chaleur à chaque instant café",
        reviews = 5.0

    ),
    Product(
        id = "3",
        name = "Layali",
        price = 25.00,
        imageResId = R.drawable.image3,
        description = "Un hommage aux nuits orientales, parfaite pour les moments calmes et profonds",
        reviews = 5.0

    ),
    Product(
        id = "4",
        name = "Sama",
        price = 21.75,
        imageResId = R.drawable.image4,
        description = "Un design céleste aux nuances bleues, pour s’évader à chaque gorgée" ,
        reviews = 5.0

    ),
    Product(
        id = "5",
        name = "Rimal",
        price = 18.90,
        imageResId = R.drawable.image5,
        description = "Inspirée des dunes dorées, cette tasse incarne la beauté du désert" ,
        reviews = 5.0

    ),
    Product(
        id = "6",
        name = "Andalous",
        price = 29.90,
        imageResId = R.drawable.image6,
        description = "Un style raffiné aux motifs andalous, alliant histoire et élégance" ,
        reviews = 5.0

    ),
    Product(
        id = "7",
        name = "Haneen",
        price = 24.60,
        imageResId = R.drawable.image7,
        description = "Délicate et nostalgique, elle évoque la douceur des souvenirs" ,
        reviews = 5.0

    ),
    Product(
        id = "8",
        name = "Rüya Classique",
        price = 27.00,
        imageResId = R.drawable.image8,
        description = "Le modèle emblématique de Rüya, où l’art rencontre l’authenticité" ,
        reviews = 5.0

    ),
    Product(
        id = "9",
        name = "Asrar",
        price = 23.40,
        imageResId = R.drawable.image9,
        description = "Mystérieuse et artistique, cette tasse séduit les amateurs de beauté singulière",
        reviews = 5.0

    ),
)

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
            DetailsScreen(productId = productId, navController = navController)

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(onNavigateToDetails: (String) -> Unit) {
    Scaffold(
        containerColor = PinkLight,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Rüya – فناجين ",
                        style = MaterialTheme.typography.titleLarge,
                        fontStyle = FontStyle.Italic,
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
            // بانر
            item(span = { GridItemSpan(maxLineSpan) }) {
                AsyncImage(
                    model = R.drawable.image, // banner doux en rose
                    contentDescription = "Banner Rüya",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
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









@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(productId: String, navController: NavController) {
    val product = products.find { it.id == productId }

    if (product == null) {
        ErrorScreen(message = "Produit non trouvé")
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(product.name) },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Retour")
                        }
                    }
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = { /* Ajouter au panier */ },
                    icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Panier") },
                    text = { Text("Ajouter - ${product.price}€") },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .background(Color(0xFFFFE4E1)) // خلفية وردية
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = product.imageResId,
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RatingStars(rating = product.rating)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "(${product.reviews} avis)", style = MaterialTheme.typography.bodySmall)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${product.price}€",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun RatingStars(rating: Float, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        val fullStars = rating.toInt()
        val hasHalfStar = (rating - fullStars) >= 0.5
        val emptyStars = 5 - fullStars - if (hasHalfStar) 1 else 0

        repeat(fullStars) {
            Icon(Icons.Filled.Star, contentDescription = "Star", tint = Color(0xFFFFC107))
        }
        if (hasHalfStar) {
            Icon(Icons.Filled.StarHalf, contentDescription = "Half Star", tint = Color(0xFFFFC107))
        }
        repeat(emptyStars) {
            Icon(Icons.Filled.StarBorder, contentDescription = "Empty Star", tint = Color(0xFFFFC107))
        }
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = Color.Red,
            style = MaterialTheme.typography.bodyLarge
        )
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
