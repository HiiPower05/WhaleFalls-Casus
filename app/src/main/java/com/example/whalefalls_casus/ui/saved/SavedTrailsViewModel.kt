package com.example.whalefalls_casus.ui.saved

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whalefalls_casus.data.local.entity.TrailEntity
import com.example.whalefalls_casus.data.repository.TrailRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SavedTrailsViewModel(
    private val repository: TrailRepository
) : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    val savedTrails: StateFlow<List<TrailEntity>> = repository.savedTrails
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            repository.savedTrails.collect { trails ->
                trails.forEach { trail ->
                    syncTrailToFirestore(trail)
                }
            }
        }
    }

    private fun syncTrailToFirestore(trail: TrailEntity) {
        val userId = auth.currentUser?.uid ?: return

        val trailData = mapOf(
            "id" to trail.id,
            "name" to trail.name,
            "location" to trail.location,
            "latitude" to trail.latitude,
            "longitude" to trail.longitude,
            "difficulty" to trail.difficulty,
            "distanceKm" to trail.distanceKm,
            "estimatedDurationHours" to trail.estimatedDurationHours,
            "description" to trail.description,
            "isSavedOffline" to trail.isSavedOffline,
            "downloadedAtTimestamp" to trail.downloadedAtTimestamp
        )

        firestore.collection("users")
            .document(userId)
            .collection("savedTrails")
            .document(trail.id)
            .set(trailData)
            .addOnFailureListener { exception ->
                Log.e("WeatherSync", "Failed to sync trail", exception)
            }
    }

    fun saveTrail(trail: TrailEntity) {
        viewModelScope.launch {
            repository.saveTrail(trail)
        }
    }

    fun downloadTrail(trail: TrailEntity) {
        viewModelScope.launch {
            repository.saveTrail(
                trail.copy(
                    isSavedOffline = true,
                    downloadedAtTimestamp = System.currentTimeMillis()
                )
            )
        }
    }

    fun removeTrail(trailId: String) {
        viewModelScope.launch {
            repository.removeTrail(trailId)

            val userId = auth.currentUser?.uid ?: return@launch

            firestore.collection("users")
                .document(userId)
                .collection("savedTrails")
                .document(trailId)
                .delete()
                .addOnFailureListener { exception ->
                    Log.e("WeatherSync", "Failed to remove cloud trail", exception)
                }
        }
    }
}