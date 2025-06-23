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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.data.Entities.Product
import com.example.myapplication.utils.CartStorage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonPanierScreen(
    onBack: () -> Unit,
    onProductClick: (String) -> Unit  // ← هذا المتغير مسؤول عن التنقل لصفحة التفاصيل
) {
    val context = LocalContext.current
    val cartProducts = remember { mutableStateListOf<Product>().apply { addAll(CartStorage.loadCart(context)) } }

    var selectedProducts by remember { mutableStateOf(setOf<String>()) }
    var quantities by remember { mutableStateOf(cartProducts.associate { it.id to 1 }.toMutableMap()) }

    val totalSelected = selectedProducts.sumOf { id ->
        val product = cartProducts.find { it.id == id }
        val quantity = quantities[id] ?: 1
        (product?.price ?: 0.0) * quantity
    }

    val allSelected = selectedProducts.size == cartProducts.size && cartProducts.isNotEmpty()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mon Panier", color = Color(0xFFD81B60)) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour", tint = Color(0xFFD81B60))
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFFFCE4EC))
            )
        },
        bottomBar = {
            if (cartProducts.isNotEmpty()) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Total sélectionné: ${"%.2f".format(totalSelected)} DH",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF880E4F))
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { /* logique de paiement */ },
                        enabled = selectedProducts.isNotEmpty(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD81B60))
                    ) {
                        Text("Passer la commande", color = Color.White)
                    }
                }
            }
        }
    ) { paddingValues ->
        if (cartProducts.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Votre panier est vide", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                // ✅ "Tout sélectionner"
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = allSelected,
                            onCheckedChange = {
                                selectedProducts = if (it) {
                                    cartProducts.map { it.id }.toSet()
                                } else {
                                    emptySet()
                                }
                            }
                        )
                        Text("Tout sélectionner", color = Color(0xFF880E4F))
                    }
                }

                items(cartProducts) { product ->
                    val isSelected = selectedProducts.contains(product.id)
                    val quantity = quantities[product.id] ?: 1
                    val productTotal = product.price * quantity

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onProductClick(product.id) }, // ✅ التنقل للتفاصيل
                        elevation = CardDefaults.cardElevation(6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8BBD0))
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isSelected,
                                onCheckedChange = {
                                    selectedProducts = if (it)
                                        selectedProducts + product.id
                                    else
                                        selectedProducts - product.id
                                }
                            )

                            AsyncImage(
                                model = product.imageUrl,
                                contentDescription = product.name,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(MaterialTheme.shapes.medium)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(product.name, style = MaterialTheme.typography.titleMedium, color = Color(0xFF880E4F))
                                Text("Prix: ${product.price} DH", color = Color(0xFF880E4F))

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(onClick = {
                                        val current = quantities[product.id] ?: 1
                                        if (current > 1) quantities[product.id] = current - 1
                                    }) {
                                        Text("-", style = MaterialTheme.typography.titleMedium)
                                    }
                                    Text("$quantity", modifier = Modifier.padding(horizontal = 8.dp))
                                    IconButton(onClick = {
                                        val current = quantities[product.id] ?: 1
                                        quantities[product.id] = current + 1
                                    }) {
                                        Text("+", style = MaterialTheme.typography.titleMedium)
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Total: ${"%.2f".format(productTotal)} DH", color = Color(0xFF6A1B9A))
                            }

                            IconButton(onClick = {
                                cartProducts.remove(product)
                                CartStorage.saveCart(context, cartProducts)
                                quantities.remove(product.id)
                                selectedProducts = selectedProducts - product.id
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Supprimer", tint = Color.Red)
                            }
                        }
                    }
                }
            }
        }
    }
}
