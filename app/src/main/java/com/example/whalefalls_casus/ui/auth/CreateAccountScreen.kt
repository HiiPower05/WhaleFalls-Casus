package com.example.whalefalls_casus.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whalefalls_casus.theme.CardSurface
import com.example.whalefalls_casus.theme.CreamBackground
import com.example.whalefalls_casus.theme.DeepGreen
import com.example.whalefalls_casus.theme.EasyGreen
import com.example.whalefalls_casus.theme.SageGreen
import com.example.whalefalls_casus.theme.SurfaceBorder
import com.example.whalefalls_casus.theme.TextPrimary
import com.example.whalefalls_casus.theme.TextSecondary
import com.example.whalefalls_casus.ui.components.MountainLandscapeBackground
import com.example.whalefalls_casus.ui.components.WhaleFallsLogo

@Composable
fun CreateAccountScreen(
    onAccountCreated: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onBackClick: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var agreedToTerms by remember { mutableStateOf(false) }

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
                .padding(top = 16.dp, bottom = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Back Arrow
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = DeepGreen
                    )
                }
            }

            WhaleFallsLogo(
                whaleHeight = 70.dp,
                compactText = false
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Create Account",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = DeepGreen
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Join WhaleFalls-Casus and start your next adventure.",
                fontSize = 14.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Full Name Input
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Full Name",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = DeepGreen,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = { Text("Enter your full name", color = TextSecondary) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Person, contentDescription = null, tint = SageGreen)
                    },
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

            Spacer(modifier = Modifier.height(12.dp))

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
                    placeholder = { Text("Enter your email address", color = TextSecondary) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Email, contentDescription = null, tint = SageGreen)
                    },
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

            Spacer(modifier = Modifier.height(12.dp))

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
                    placeholder = { Text("Create a password", color = TextSecondary) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Lock, contentDescription = null, tint = SageGreen)
                    },
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

                // Requirement note
                Row(
                    modifier = Modifier.padding(top = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = null,
                        tint = EasyGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = " At least 8 characters with a number and a symbol",
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Confirm Password Input
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Confirm Password",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = DeepGreen,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    placeholder = { Text("Confirm your password", color = TextSecondary) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Lock, contentDescription = null, tint = SageGreen)
                    },
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                imageVector = if (confirmPasswordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                                contentDescription = null,
                                tint = SageGreen
                            )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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

            Spacer(modifier = Modifier.height(12.dp))

            // Checkbox Terms
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = agreedToTerms,
                    onCheckedChange = { agreedToTerms = it },
                    colors = CheckboxDefaults.colors(checkedColor = DeepGreen)
                )
                Text(
                    text = "I agree to the ",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
                Text(
                    text = "Terms of Service",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepGreen,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { }
                )
                Text(
                    text = " and ",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
                Text(
                    text = "Privacy Policy",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepGreen,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Create Account Button
            Button(
                onClick = onAccountCreated,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DeepGreen,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = "Create Account",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // OR Divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(modifier = Modifier.weight(1f), color = SurfaceBorder)
                Text(text = "  OR  ", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextSecondary)
                Divider(modifier = Modifier.weight(1f), color = SurfaceBorder)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Sign up with Google Button
            Surface(
                onClick = onAccountCreated,
                shape = RoundedCornerShape(12.dp),
                color = CardSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "G ", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF4285F4))
                    Text(text = "Sign up with Google", fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = TextPrimary)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Footer Link
            Row {
                Text(text = "Already have an account? ", fontSize = 14.sp, color = TextSecondary)
                Text(
                    text = "Log in",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepGreen,
                    modifier = Modifier.clickable { onNavigateToLogin() }
                )
            }
        }

        // Bottom Background
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            MountainLandscapeBackground(height = 120.dp)
        }
    }
}
