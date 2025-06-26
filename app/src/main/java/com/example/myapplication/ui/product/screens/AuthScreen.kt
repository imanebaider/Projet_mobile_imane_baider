package com.example.myapplication.ui.product.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R  // تأكد من مسار package ديالك

@Composable
fun AuthScreen(
    context: Context,
    onLoginSuccess: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf(false) }

    val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val savedEmail = prefs.getString("email", null)
    val savedPassword = prefs.getString("password", null)

    // تدرج لوني شفاف باش نراكبوه فوق الصورة
    val gradientOverlay = Brush.verticalGradient(
        colors = listOf(Color(0xAAFCE4EC), Color(0xAAF8BBD0))
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // صورة الخلفية تغطي كامل الشاشة
        Image(
            painter = painterResource(id = R.drawable.background_image), // بدل باسم الصورة ديالك
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // تراكب التدرج اللوني فوق الصورة
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientOverlay)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Bienvenue !",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = Color(0xFFD81B60),
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            emailError = false
                            loginError = false
                        },
                        label = { Text("Email") },
                        isError = emailError,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = if (emailError) Color.Red else Color(0xFFD81B60),
                            cursorColor = if (emailError) Color.Red else Color(0xFFD81B60)
                        )
                    )
                    if (emailError) {
                        Text(
                            text = "Email invalide",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.align(Alignment.Start).padding(start = 16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = false
                            loginError = false
                        },
                        label = { Text("Mot de passe") },
                        isError = passwordError,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = if (passwordError) Color.Red else Color(0xFFD81B60),
                            cursorColor = if (passwordError) Color.Red else Color(0xFFD81B60)
                        )
                    )
                    if (passwordError) {
                        Text(
                            text = "Le mot de passe est trop court",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.align(Alignment.Start).padding(start = 16.dp)
                        )
                    }

                    if (loginError) {
                        Text(
                            text = "Email ou mot de passe incorrect",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            val emailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                            val passwordValid = password.length >= 6

                            emailError = !emailValid
                            passwordError = !passwordValid

                            if (emailValid && passwordValid) {
                                if (email == savedEmail && password == savedPassword) {
                                    loginError = false
                                    onLoginSuccess()
                                } else {
                                    loginError = true
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD81B60))
                    ) {
                        Text("Se connecter", color = Color.White, fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    TextButton(onClick = { onNavigateToSignUp() }) {
                        Text("Créer un nouveau compte", color = Color(0xFF6A1B9A))
                    }
                }
            }
        }
    }
}