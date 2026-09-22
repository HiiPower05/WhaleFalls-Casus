package com.example.whalefalls_casus.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.whalefalls_casus.data.local.dao.EmergencyContactDao
import com.example.whalefalls_casus.data.local.dao.TrailDao
import com.example.whalefalls_casus.data.local.entity.EmergencyContactEntity
import com.example.whalefalls_casus.data.local.entity.TrailEntity

@Database(
    entities = [TrailEntity::class, EmergencyContactEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trailDao(): TrailDao
    abstract fun emergencyContactDao(): EmergencyContactDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "whalefalls_casus_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}