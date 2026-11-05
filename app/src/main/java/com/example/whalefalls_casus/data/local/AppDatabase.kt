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
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [TrailEntity::class, EmergencyContactEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trailDao(): TrailDao
    abstract fun emergencyContactDao(): EmergencyContactDao

    companion object {
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE trails ADD COLUMN latitude REAL NOT NULL DEFAULT 0.0"
                )
                db.execSQL(
                    "ALTER TABLE trails ADD COLUMN longitude REAL NOT NULL DEFAULT 0.0"
                )
            }
        }
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "whalefalls_casus_db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}