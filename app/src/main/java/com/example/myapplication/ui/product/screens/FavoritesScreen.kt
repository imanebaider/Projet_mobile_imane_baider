package com.example.myapplication.ui.product.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.data.Entities.Product
import com.example.myapplication.utils.FavoriteStorage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onNavigateToDetails: (String) -> Unit,
    onBack: () -> Unit // إضافة دالة الرجوع
) {
    val context = LocalContext.current
    val favoriteProducts = remember { mutableStateListOf<Product>() }
    val scope = rememberCoroutineScope()

    // تحميل المفضلات من SharedPreferences عند الدخول للصفحة
    LaunchedEffect(Unit) {
        favoriteProducts.clear()
        favoriteProducts.addAll(FavoriteStorage.getFavorites(context))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mes Favoris") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Retour"
                        )
                    }
                }
            )
        }
    ) { padding ->

        if (favoriteProducts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Aucun produit dans vos favoris.", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favoriteProducts, key = { it.id }) { product ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToDetails(product.id) },
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFCE4EC))
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // صورة المنتج
                            AsyncImage(
                                model = product.imageUrl,
                                contentDescription = product.name,
                                modifier = Modifier.size(80.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    product.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color(0xFF880E4F)
                                )
                                Text(
                                    "${product.price} DH",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF6A1B9A)
                                )
                            }
                            IconButton(
                                onClick = {
                                    // حذف المنتج من المفضلات
                                    scope.launch {
                                        FavoriteStorage.toggleFavorite(context, product)
                                        // تحديث القائمة في الواجهة
                                        favoriteProducts.clear()
                                        favoriteProducts.addAll(FavoriteStorage.getFavorites(context))
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Supprimer des favoris",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
