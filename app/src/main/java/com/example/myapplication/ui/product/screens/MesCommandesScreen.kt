package com.example.myapplication.ui.product.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.data.Entities.Order
import com.example.myapplication.data.Entities.OrderItem
import com.example.myapplication.data.Entities.Product
import com.example.myapplication.utils.CartStorage
import com.example.myapplication.utils.OrderStorage
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MesCommandesScreen(
    onBack: () -> Unit,
    onProductClick: (String) -> Unit,
) {
    val context = LocalContext.current

    // تحميل كل الطلبات
    val orders = remember { mutableStateListOf<Order>() }

    // تحميل بيانات المنتجات (مثلاً من الكارت أو مصدر آخر)
    val allProducts = remember { mutableStateListOf<Product>().apply { addAll(CartStorage.loadCart(context)) } }

    LaunchedEffect(Unit) {
        orders.clear()
        orders.addAll(OrderStorage.loadOrders(context))
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mes Commandes", color = Color(0xFFD81B60)) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack,
                            contentDescription = "Retour",
                            tint = Color(0xFFD81B60)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFFFCE4EC))
            )
        }
    ) { paddingValues ->
        if (orders.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Vous n'avez aucune commande.", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(orders) { order ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8BBD0)),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Commande du ${java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(order.timestamp)}",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF880E4F)
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            // عرض المنتجات داخل الطلب
                            order.items.forEach { orderItem ->
                                val product = allProducts.find { it.id == orderItem.productId }
                                if (product != null) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp)
                                            .clickable { onProductClick(product.id) },
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        AsyncImage(
                                            model = product.imageUrl,
                                            contentDescription = product.name,
                                            modifier = Modifier.size(60.dp)
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(product.name, color = Color(0xFF6A1B9A))
                                            Text("Quantité: ${orderItem.quantity}", style = MaterialTheme.typography.bodySmall)
                                            Text("Prix unitaire: ${product.price} DH", style = MaterialTheme.typography.bodySmall)
                                        }
                                        val totalPrice = product.price * orderItem.quantity
                                        Text(
                                            "%.2f DH".format(totalPrice),
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color(0xFFD81B60)
                                        )
                                    }
                                } else {
                                    Text(
                                        "Produit non trouvé: ${orderItem.productId}",
                                        color = Color.Red,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
