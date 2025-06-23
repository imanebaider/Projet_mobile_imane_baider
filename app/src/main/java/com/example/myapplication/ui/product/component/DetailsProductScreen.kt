package com.example.emtyapp.ui.product.details
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.product.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    productId: String,
    navController: NavController,
    viewModel: ProductViewModel
) {
    val product = viewModel.getProduct(productId)

    println("🔎 productId reçu: '$productId'")
    println("📦 Liste des produits dans cache:")
    viewModel.state.value.let { state ->
        if (state is com.example.myapplication.viewmodel.ProductState.Success) {
            state.products.forEach {
                println("🆔 Produit: id='${it.id}', name='${it.name}'")
            }
        } else {
            println("⚠️ Le state n'est pas Success: $state")
        }
    }
    println("🧪 Produit trouvé: ${product?.name}")


    Scaffold(
        topBar = {
            TopAppBar(
                title = { product?.name?.let { Text(it) } },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        product?.let {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineMedium
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    RatingStars(rating = product.reviews.toFloat())

                    Spacer(modifier = Modifier.width(8.dp))
                    Text("(${product.reviews} reviews)")
                }

                Text(
                    text = "€${product.price}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } ?: ErrorScreen("Product not found")
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
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }
}



@Composable
fun RatingStars(rating: Float) {
    Row {
        repeat(5) { index ->
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Rating",
                tint = if (index < rating.toInt()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
            )
        }
    }
}
