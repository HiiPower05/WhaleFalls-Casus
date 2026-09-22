package com.example.whalefalls_casus.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.whalefalls_casus.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _currentUser = MutableStateFlow(User())
    val currentUser: StateFlow<User> = _currentUser.asStateFlow()

    // --- App Settings States ---
    var darkModeEnabled by mutableStateOf(false)
        private set

    var trackingEnabled by mutableStateOf(true)
        private set

    var selectedUnits by mutableStateOf("Metric (km, m, °C)")
        private set

    var selectedLanguage by mutableStateOf("English")
        private set

    fun setDarkMode(enabled: Boolean) {
        darkModeEnabled = enabled
    }

    fun setTracking(enabled: Boolean) {
        trackingEnabled = enabled
    }

    fun setUnits(units: String) {
        selectedUnits = units
    }

    fun setLanguage(language: String) {
        selectedLanguage = language
    }
    // ---------------------------

    init {
        // Auto-login if user session is still active in Firebase Auth
        auth.currentUser?.let { firebaseUser ->
            fetchUserProfile(firebaseUser.uid)
        }
    }
    fun signInWithGoogleCredential(idToken: String, onResult: (Boolean, String?) -> Unit) {
        val credential = com.google.firebase.auth.GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnSuccessListener { result ->
                val firebaseUser = result.user
                val uid = firebaseUser?.uid ?: return@addOnSuccessListener
                val fullName = firebaseUser.displayName ?: "Google User"
                val email = firebaseUser.email ?: ""

                // Check if user profile already exists in Firestore, if not create it
                db.collection("users").document(uid).get()
                    .addOnSuccessListener { document ->
                        if (!document.exists()) {
                            val userMap = hashMapOf("fullName" to fullName, "email" to email)
                            db.collection("users").document(uid).set(userMap)
                        }
                        fetchUserProfile(uid)
                        onResult(true, null)
                    }
                    .addOnFailureListener { e ->
                        onResult(false, e.localizedMessage)
                    }
            }
            .addOnFailureListener { e ->
                onResult(false, e.localizedMessage ?: "Google Sign-In failed")
            }
    }

    fun signUpUser(fullName: String, email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid ?: return@addOnSuccessListener
                val userMap = hashMapOf("fullName" to fullName, "email" to email)

                db.collection("users").document(uid).set(userMap)
                    .addOnSuccessListener {
                        _currentUser.value = User(fullName = fullName, email = email, isLoggedIn = true)
                        onResult(true, null)
                    }
                    .addOnFailureListener { e ->
                        onResult(false, e.localizedMessage)
                    }
            }
            .addOnFailureListener { e ->
                onResult(false, e.localizedMessage)
            }
    }

    fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email.trim(), password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid
                if (uid != null) {
                    fetchUserProfile(uid)
                    onResult(true, null)
                } else {
                    onResult(false, "User ID not found.")
                }
            }
            .addOnFailureListener { e ->
                onResult(false, e.localizedMessage ?: "Authentication failed")
            }
    }

    private fun fetchUserProfile(uid: String) {
        db.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val fullName = document.getString("fullName") ?: ""
                    val email = document.getString("email") ?: ""
                    _currentUser.value = User(fullName = fullName, email = email, isLoggedIn = true)
                }
            }
    }

    fun signOut() {
        auth.signOut()
        _currentUser.value = User(isLoggedIn = false)
    }
}