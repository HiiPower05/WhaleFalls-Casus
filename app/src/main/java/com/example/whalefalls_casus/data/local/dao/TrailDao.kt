package com.example.whalefalls_casus.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.whalefalls_casus.data.local.entity.TrailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrailDao {

    @Query("SELECT * FROM trails ORDER BY downloadedAtTimestamp DESC")
    fun getAllSavedTrails(): Flow<List<TrailEntity>>

    @Query("SELECT * FROM trails WHERE id = :trailId")
    suspend fun getTrailById(trailId: String): TrailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrail(trail: TrailEntity)

    @Delete
    suspend fun deleteTrail(trail: TrailEntity)

    @Query("DELETE FROM trails WHERE id = :trailId")
    suspend fun deleteTrailById(trailId: String)
}