package com.example.whalefalls_casus.data.repository

import com.example.whalefalls_casus.data.local.dao.EmergencyContactDao
import com.example.whalefalls_casus.data.local.entity.EmergencyContactEntity
import kotlinx.coroutines.flow.Flow

class EmergencyContactRepository(private val contactDao: EmergencyContactDao) {

    val contacts: Flow<List<EmergencyContactEntity>> = contactDao.getAllContacts()

    suspend fun addContact(contact: EmergencyContactEntity) {
        contactDao.insertContact(contact)
    }

    suspend fun deleteContact(contact: EmergencyContactEntity) {
        contactDao.deleteContact(contact)
    }
}