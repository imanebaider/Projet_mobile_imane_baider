package com.example.myapplication.ui.product.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

data class ClientInfo(
    val nom: String,
    val adresse: String,
    val telephone: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidationCommandeScreen(
    onBack: () -> Unit,
    onConfirmPayment: (clientInfo: ClientInfo, paymentMethod: String) -> Unit
) {
    // 🎨 Couleurs
    val RoseFonce = Color(0xFFD81B60)
    val TexteFonce = Color(0xFF880E4F)

    var nom by remember { mutableStateOf("") }
    var adresse by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf("") }
    var selectedPaymentMethod by remember { mutableStateOf("Carte bancaire") }

    val paymentMethods = listOf(
        PaymentMethod("Carte bancaire", R.drawable.visa_logo),
        PaymentMethod("PayPal", R.drawable.paypal_logo),
        PaymentMethod("Paiement à la livraison", R.drawable.cash_logo)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Validation de la commande",
                        color = RoseFonce,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour", tint = RoseFonce)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .background(Color.White),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = nom,
                    onValueChange = { nom = it },
                    label = { Text("Nom complet") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                OutlinedTextField(
                    value = adresse,
                    onValueChange = { adresse = it },
                    label = { Text("Adresse de livraison") },
                    maxLines = 3,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                OutlinedTextField(
                    value = telephone,
                    onValueChange = { telephone = it },
                    label = { Text("Téléphone") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Text(
                    text = "Méthode de paiement",
                    color = TexteFonce,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                // ✅ Section Paiement Stylée
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    paymentMethods.forEach { method ->
                        val selected = method.name == selectedPaymentMethod
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (selected) Color(0xFFF8BBD0) else Color(0xFFF5F5F5),
                            shadowElevation = if (selected) 4.dp else 1.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedPaymentMethod = method.name }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = method.iconRes),
                                    contentDescription = method.name,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape),
                                    tint = Color.Unspecified // conserve la couleur d’origine
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    method.name,
                                    fontSize = 16.sp,
                                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                                    color = TexteFonce
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val clientInfo = ClientInfo(nom, adresse, telephone)
                        onConfirmPayment(clientInfo, selectedPaymentMethod)
                    },
                    enabled = nom.isNotBlank() && adresse.isNotBlank() && telephone.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(containerColor = RoseFonce),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Passer au paiement", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    )
}

data class PaymentMethod(val name: String, val iconRes: Int)
