package com.example.myapplication.ui.product.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R  // تأكد من مسار package ديالك

@Composable
fun ProfileScreen(
    context: Context,
    onLogout: () -> Unit
) {
    val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    var email by remember { mutableStateOf(prefs.getString("email", "") ?: "") }

    // تدرج لوني وردي شفاف باش يبين فوق الصورة
    val pinkGradient = Brush.verticalGradient(
        colors = listOf(Color(0x88FFC1E3), Color(0x88F48FB1))
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // صورة الخلفية تغطي كامل الشاشة
        Image(
            painter = painterResource(id = R.drawable.background_image), // بدل هادي باسم الصورة ديالك
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // تراكب التدرج اللوني فوق الصورة
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(pinkGradient)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // أفاتار الدائرة فيه أول حرف من الإيميل
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF48FB1)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (email.isNotEmpty()) email[0].uppercase() else "?",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 48.sp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Profil utilisateur",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = Color(0xFFD81B60),
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Email : $email",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 18.sp,
                        color = Color(0xFF880E4F)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            prefs.edit().clear().apply()
                            onLogout()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD81B60))
                    ) {
                        Text(
                            "Se déconnecter",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}