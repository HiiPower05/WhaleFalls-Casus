package com.example.whalefalls_casus

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.lifecycleScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.example.whalefalls_casus.navigation.AppNavigation
import com.example.whalefalls_casus.theme.CreamBackground
import com.example.whalefalls_casus.theme.WhaleFallsCasusTheme
import com.example.whalefalls_casus.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    // Initialize your UserViewModel
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhaleFallsCasusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = CreamBackground
                ) {
                    // Pass userViewModel and the Google sign-in trigger down to your navigation/screens
                    AppNavigation(
                        userViewModel = userViewModel,
                        onGoogleSignInClicked = {
                            triggerGoogleSignIn()
                        }
                    )
                }
            }
        }
    }

    // --- Credential Manager Google Sign-In Logic ---
    private fun triggerGoogleSignIn() {
        val rawClientId = "227790233233-rlqcdfna7bavs8n5vpls1487n6es0i19.apps.googleusercontent.com"

        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(rawClientId)
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val credentialManager = CredentialManager.create(this)

        // Use lifecycleScope so the coroutine automatically cancels if the Activity stops
        lifecycleScope.launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = this@MainActivity
                )
                val credential = result.credential

                if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(credential.data)
                    val googleIdToken = googleIdTokenCredential.idToken

                    // Send token to ViewModel for Firebase authentication
                    userViewModel.signInWithGoogleCredential(googleIdToken) { success, error ->
                        if (success) {
                            Toast.makeText(
                                this@MainActivity,
                                "Google Sign-In Successful!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                error ?: "Sign-in failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Log.e("Auth", "Unexpected credential type: ${credential.type}")
                }
            } catch (e: GetCredentialException) {
                Log.e("Auth", "Sign-in failed or cancelled: ${e.message}", e)
                Toast.makeText(
                    this@MainActivity,
                    "Sign-in failed: ${e.localizedMessage}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}