package com.example.myapplication.ui.product.screens
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.Entities.Product
import com.example.myapplication.ui.product.ProductIntent
import com.example.myapplication.ui.product.ProductViewModel
import com.example.myapplication.ui.product.components.ProductItem
import com.example.myapplication.viewmodel.ProductState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.emtyapp.ui.product.details.ErrorScreen
import com.example.myapplication.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: ProductViewModel = viewModel(),
    onNavigateToDetails: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(ProductIntent.LoadProducts)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Rüya – فناجين") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Banner Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            CenteredTitle()

            when (state) {
                is ProductState.Loading -> {
                    LoadingScreen(modifier = Modifier.weight(1f))

                }

                is ProductState.Success -> {
                    val products = (state as ProductState.Success).products
                    ProductGrid(
                        products = products,
                        modifier = Modifier.weight(1f),
                        onItemClick = onNavigateToDetails
                    )

                }

                is ProductState.Error -> {
                    ErrorScreen(
                        (state as ProductState.Error).message,
                        modifier = Modifier.weight(1f)
                    )

                }
            }

        }
    }
    Footer()
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Loading...", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ErrorScreen(message: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }
}

@Composable
private fun ProductGrid(
    products: List<Product>,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(products) { product ->
            ProductItem(
                product = product,
                onClick = { onItemClick(product.id) }
            )
        }
    }
}

@Composable

fun CenteredTitle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Our Products",
            style = MaterialTheme.typography.headlineSmall.copy(  // نص أصغر من headlineMedium
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF6A1B9A) // بنفسجي معتدل أنيق
            )
        )
    }
}

@Composable

fun Footer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFDC7399)) // موف فاتح
            .padding(20.dp)
    ) {
        // Newsletter section
        Text(
            text = "Don’t miss a thing",
            color = Color(0xFFD890E8),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = "Enter your email to know about new collections & launches.",
            color = Color(0xFFEB3BFA),
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Your Email") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Links section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Services", fontWeight = FontWeight.Bold, color = Color(0xFF4A148C))
                Spacer(modifier = Modifier.height(6.dp))
                Text("About Us", color = Color(0xFF6A1B9A))
                Text("Delivery Info", color = Color(0xFF6A1B9A))
                Text("Privacy Policy", color = Color(0xFF6A1B9A))
            }

            Column {
                Text("Company", fontWeight = FontWeight.Bold, color = Color(0xFF4A148C))
                Spacer(modifier = Modifier.height(6.dp))
                Text("CASABLANCA Aïn Chock", color = Color(0xFF6A1B9A))
                Text("Nissu Store", color = Color(0xFF6A1B9A))
                Text("0769396755", color = Color(0xFF6A1B9A))
                Text("Contact@nissu.ma", color = Color(0xFF6A1B9A))
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Follow Us", fontWeight = FontWeight.Bold, color = Color(0xFF4A148C))
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "Facebook",
                        tint = Color(0xFF4A148C),
                        modifier = Modifier.size(24.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_instagram),
                        contentDescription = "Instagram",
                        tint = Color(0xFF4A148C),
                        modifier = Modifier.size(24.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_tiktok),
                        contentDescription = "TikTok",
                        tint = Color(0xFF4A148C),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Divider(color = Color(0xFFBA68C8))

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "© 2025 Nissu. All Rights Reserved.",
            color = Color(0xFF4A148C),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
