package com.example.whalefalls_casus.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whalefalls_casus.data.local.entity.TrailEntity
import com.example.whalefalls_casus.data.repository.TrailRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SavedTrailsViewModel(
    private val repository: TrailRepository
) : ViewModel() {

    val savedTrails: StateFlow<List<TrailEntity>> = repository.savedTrails
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun saveTrail(trail: TrailEntity) {
        viewModelScope.launch {
            repository.saveTrail(trail)
        }
    }

    fun removeTrail(trailId: String) {
        viewModelScope.launch {
            repository.removeTrail(trailId)
        }
    }
}