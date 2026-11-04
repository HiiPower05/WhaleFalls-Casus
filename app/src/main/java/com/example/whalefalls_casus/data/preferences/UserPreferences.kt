package com.example.whalefalls_casus.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.booleanPreferencesKey
private val Context.dataStore by preferencesDataStore(
    name = "user_settings"
)

object UserPreferences {

    private val UNITS = stringPreferencesKey("units")
    private val LANGUAGE = stringPreferencesKey("language")
    private val TRACKING_ENABLED = booleanPreferencesKey("tracking_enabled")

    fun getUnits(context: Context): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[UNITS] ?: "Metric"
        }
    }

    fun getLanguage(context: Context): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[LANGUAGE] ?: "English"
        }
    }

    suspend fun saveUnits(context: Context, units: String) {
        context.dataStore.edit { preferences ->
            preferences[UNITS] = units
        }
    }

    suspend fun saveLanguage(context: Context, language: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE] = language
        }
    }
    fun getTrackingEnabled(context: Context): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[TRACKING_ENABLED] ?: true
        }
    }

    suspend fun saveTrackingEnabled(context: Context, enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[TRACKING_ENABLED] = enabled
        }
    }
}