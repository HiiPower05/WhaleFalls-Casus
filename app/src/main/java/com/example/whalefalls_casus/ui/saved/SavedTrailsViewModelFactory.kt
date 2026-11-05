package com.example.whalefalls_casus.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whalefalls_casus.data.repository.TrailRepository

class SavedTrailsViewModelFactory(
    private val repository: TrailRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedTrailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SavedTrailsViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}