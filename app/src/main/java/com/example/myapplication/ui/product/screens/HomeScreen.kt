package com.example.myapplication.ui.product.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.data.Entities.Product
import com.example.myapplication.ui.product.ProductIntent
import com.example.myapplication.ui.product.ProductViewModel
import com.example.myapplication.ui.product.components.ProductItem
import com.example.myapplication.ui.product.details.ErrorScreen
import com.example.myapplication.utils.CartStorage
import com.example.myapplication.viewmodel.ProductState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: ProductViewModel,
    onNavigateToDetails: (String) -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToOrders: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    var searchQuery by remember { mutableStateOf("") }

    val filteredProducts = when (state) {
        is ProductState.Success -> (state as ProductState.Success).products.filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }
        else -> emptyList()
    }

    LaunchedEffect(Unit) {
        viewModel.handleIntent(ProductIntent.LoadProducts)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            CustomDrawerContent(
                onCloseDrawer = { scope.launch { drawerState.close() } },
                onNavigateToProducts = { scope.launch { drawerState.close() } },
                onNavigateToCart = {
                    scope.launch { drawerState.close() }
                    onNavigateToCart()
                },
                onNavigateToOrders = {
                    scope.launch { drawerState.close() }
                    onNavigateToOrders()
                },
                onNavigateToFavorites = {
                    scope.launch { drawerState.close() }
                    onNavigateToFavorites()
                },
                onNavigateToProfile = {
                    scope.launch { drawerState.close() }
                    onNavigateToProfile()
                },
                onNavigateToLogin = {
                    scope.launch { drawerState.close() }
                    onNavigateToLogin()
                }
            )
        }
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Rüya – فناجين") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Rechercher un produit...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

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
                    is ProductState.Loading -> LoadingScreen(modifier = Modifier.weight(1f))
                    is ProductState.Success -> {
                        ProductGrid(
                            products = filteredProducts,
                            modifier = Modifier.weight(1f),
                            onItemClick = onNavigateToDetails,
                            onAddToCart = { product ->
                                CartStorage.addToCart(context, product)
                                scope.launch {
                                    snackbarHostState.showSnackbar("${product.name} ajouté au panier")
                                }
                            }
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
    }
}

@Composable
fun CustomDrawerContent(
    onCloseDrawer: () -> Unit,
    onNavigateToProducts: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToOrders: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToLogin: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCE4EC))
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "Rüya",
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color(0xFF880E4F),
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        DrawerMenuItem(icon = Icons.Default.Home, title = "Produits", onClick = onNavigateToProducts)
        DrawerMenuItem(icon = Icons.Default.ShoppingCart, title = "Mon Panier", onClick = onNavigateToCart)
        DrawerMenuItem(icon = Icons.Default.ListAlt, title = "Mes Commandes", onClick = onNavigateToOrders)
        DrawerMenuItem(icon = Icons.Default.Favorite, title = "Mes Favoris", onClick = onNavigateToFavorites)
        DrawerMenuItem(icon = Icons.Default.Person, title = "Mon Profil", onClick = onNavigateToProfile)

        Spacer(modifier = Modifier.weight(1f))

        Divider(color = Color(0xFF880E4F).copy(alpha = 0.3f), thickness = 1.dp)

        DrawerMenuItem(
            icon = Icons.Default.Login,
            title = "Se connecter",
            onClick = onNavigateToLogin,
            tint = Color(0xFFD81B60)
        )
    }
}

@Composable
fun DrawerMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    onClick: () -> Unit,
    tint: Color = Color(0xFF880E4F)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title, tint = tint, modifier = Modifier.size(28.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
            color = tint
        )
    }
}

@Composable
fun ProductGrid(
    products: List<Product>,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit,
    onAddToCart: (Product) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(products) { product ->
            ProductItem(
                product = product,
                onClick = { onItemClick(product.id) },
                onAddToCart = { onAddToCart(product) }
            )
        }
    }
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
fun CenteredTitle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Our Products",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF6A1B9A)
            )
        )
    }
}
