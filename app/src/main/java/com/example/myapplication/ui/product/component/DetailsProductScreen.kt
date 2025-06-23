package com.example.myapplication.ui.product.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.ui.product.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    productId: String,
    navController: NavController,
    viewModel: ProductViewModel
) {
    val product = viewModel.getProduct(productId)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    product?.name?.let {
                        Text(text = it, color = Color(0xFFAD1457))
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFFAD1457)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // action du panier
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Panier",
                            tint = Color(0xFFAD1457)
                        )
                    }
                },
                modifier = Modifier.background(Color(0xFFFCE4EC))
            )
        },
        containerColor = Color(0xFFFFF0F6)
    ) { padding ->
        product?.let {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(Color(0xFFF48FB1), Color(0x00F48FB1)),
                                center = Offset(500f, 500f),
                                radius = 800f
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = product.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineMedium.copy(color = Color(0xFF880E4F))
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RatingStars(rating = product.reviews.toFloat())
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "(${product.reviews} reviews)",
                        color = Color(0xFFAD1457),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "€${product.price}",
                    style = MaterialTheme.typography.headlineSmall.copy(color = Color(0xFFC2185B))
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF4A148C),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } ?: ErrorScreen("Produit non trouvé")
    }
}

@Composable
fun RatingStars(rating: Float) {
    Row {
        repeat(5) { index ->
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Rating",
                tint = if (index < rating.toInt()) Color(0xFFD81B60) else Color(0xFFD81B6030)
            )
        }
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message, color = Color(0xFFD32F2F))
    }
}
