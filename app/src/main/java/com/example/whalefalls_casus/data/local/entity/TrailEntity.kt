package com.example.whalefalls_casus.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trails")
data class TrailEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val location: String,
    val difficulty: String,
    val distanceKm: Double,
    val estimatedDurationHours: Double,
    val description: String,
    val isSavedOffline: Boolean = false,
    val downloadedAtTimestamp: Long = System.currentTimeMillis()
)