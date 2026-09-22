
package com.example.whalefalls_casus.local
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.whalefalls_casus.data.local.AppDatabase
import com.example.whalefalls_casus.data.local.dao.EmergencyContactDao
import com.example.whalefalls_casus.data.local.dao.TrailDao
import com.example.whalefalls_casus.data.local.entity.EmergencyContactEntity
import com.example.whalefalls_casus.data.local.entity.TrailEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var db: AppDatabase
    private lateinit var trailDao: TrailDao
    private lateinit var contactDao: EmergencyContactDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        trailDao = db.trailDao()
        contactDao = db.emergencyContactDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndReadEmergencyContact() = runBlocking {
        val contact = EmergencyContactEntity(
            id = 1,
            name = "John Doe",
            relationship = "Brother",
            phoneNumber = "0821234567",
            isPrimary = true
        )
        contactDao.insertContact(contact)

        val contactsList = contactDao.getAllContacts().first()
        assertEquals(1, contactsList.size)
        assertEquals("John Doe", contactsList[0].name)
        assertTrue(contactsList[0].isPrimary)
    }

    @Test
    fun insertAndReadSavedTrail() = runBlocking {
        val trail = TrailEntity(
            id = "trail_001",
            name = "Whale Coastal Route",
            location = "Hermanus",
            difficulty = "Moderate",
            distanceKm = 12.5,
            estimatedDurationHours = 3.5,
            description = "A scenic coastal trail for whale watching.",
            isSavedOffline = true
        )
        trailDao.insertTrail(trail)

        val savedTrail = trailDao.getTrailById("trail_001")
        assertEquals("Whale Coastal Route", savedTrail?.name)
        assertEquals(12.5, savedTrail?.distanceKm ?: 0.0, 0.01)
    }
}