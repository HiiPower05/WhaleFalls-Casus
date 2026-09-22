package com.example.whalefalls_casus.ui.auth

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whalefalls_casus.theme.*
import com.example.whalefalls_casus.ui.components.MountainLandscapeBackground
import com.example.whalefalls_casus.ui.components.WhaleFallsLogo
import com.example.whalefalls_casus.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    userViewModel: UserViewModel,
    onGoogleSignInClicked: () -> Unit = {},
    onLoginSuccess: (String, String) -> Unit,
    onGoogleLogin: (String, String) -> Unit = { _, _ -> },
    onNavigateToSignUp: () -> Unit
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showGooglePicker by remember { mutableStateOf(false) }

    fun isValidEmail(target: String): Boolean {
        return target.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(top = 40.dp, bottom = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WhaleFallsLogo(whaleHeight = 80.dp, compactText = false)
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Welcome Back",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = DeepGreen
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Sign in to continue your adventure",
                fontSize = 14.sp,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Email Input
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Email",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = DeepGreen,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Enter your email", color = TextSecondary) },
                    leadingIcon = { Icon(imageVector = Icons.Outlined.Email, contentDescription = null, tint = SageGreen) },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = CardSurface,
                        unfocusedContainerColor = CardSurface,
                        focusedBorderColor = DeepGreen,
                        unfocusedBorderColor = SurfaceBorder
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Password Input
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Password",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = DeepGreen,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Enter your password", color = TextSecondary) },
                    leadingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = null, tint = SageGreen) },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                                contentDescription = null,
                                tint = SageGreen
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = CardSurface,
                        unfocusedContainerColor = CardSurface,
                        focusedBorderColor = DeepGreen,
                        unfocusedBorderColor = SurfaceBorder
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Log In Button
            Button(
                onClick = {
                    when {
                        !isValidEmail(email) -> Toast.makeText(context, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                        password.isBlank() -> Toast.makeText(context, "Please enter your password", Toast.LENGTH_SHORT).show()
                        else -> onLoginSuccess(email, password)
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DeepGreen, contentColor = Color.White),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Log In", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = SurfaceBorder)
                Text(text = "  OR  ", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextSecondary)
                HorizontalDivider(modifier = Modifier.weight(1f), color = SurfaceBorder)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Google Button
            Surface(
                onClick = { onGoogleSignInClicked() },
                shape = RoundedCornerShape(12.dp),
                color = CardSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "G ", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF4285F4))
                    Text(text = "Continue with Google", fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = TextPrimary)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row {
                Text(text = "Don't have an account? ", fontSize = 14.sp, color = TextSecondary)
                Text(
                    text = "Sign up",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepGreen,
                    modifier = Modifier.clickable { onNavigateToSignUp() }
                )
            }
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            MountainLandscapeBackground(height = 120.dp)
        }

        // Google Account Selector Sheet
        if (showGooglePicker) {
            ModalBottomSheet(onDismissRequest = { showGooglePicker = false }) {
                Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                    Text(text = "Choose an account", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text(text = "to continue to WhaleFalls-Casus", fontSize = 13.sp, color = TextSecondary)
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showGooglePicker = false
                                onGoogleLogin("user@gmail.com", "Google Account User")
                            }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Outlined.Person, contentDescription = null, tint = DeepGreen, modifier = Modifier.size(32.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = "Google User", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(text = "user@gmail.com", fontSize = 12.sp, color = TextSecondary)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}