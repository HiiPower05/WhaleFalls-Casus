package com.example.whalefalls_casus.data.repository

import com.example.whalefalls_casus.data.local.dao.TrailDao
import com.example.whalefalls_casus.data.local.entity.TrailEntity
import kotlinx.coroutines.flow.Flow

class TrailRepository(private val trailDao: TrailDao) {

    val savedTrails: Flow<List<TrailEntity>> = trailDao.getAllSavedTrails()

    suspend fun saveTrail(trail: TrailEntity) {
        trailDao.insertTrail(trail)
    }

    suspend fun removeTrail(trailId: String) {
        trailDao.deleteTrailById(trailId)
    }
}