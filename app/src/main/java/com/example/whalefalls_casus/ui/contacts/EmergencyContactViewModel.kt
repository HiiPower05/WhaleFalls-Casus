package com.example.whalefalls_casus.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whalefalls_casus.data.local.entity.EmergencyContactEntity
import com.example.whalefalls_casus.data.repository.EmergencyContactRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EmergencyContactViewModel(
    private val repository: EmergencyContactRepository
) : ViewModel() {

    val contacts: StateFlow<List<EmergencyContactEntity>> = repository.contacts
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addContact(name: String, relationship: String, phoneNumber: String, isPrimary: Boolean) {
        viewModelScope.launch {
            val contact = EmergencyContactEntity(
                name = name,
                relationship = relationship,
                phoneNumber = phoneNumber,
                isPrimary = isPrimary
            )
            repository.addContact(contact)
        }
    }

    fun deleteContact(contact: EmergencyContactEntity) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }
}