package com.example.whalefalls_casus.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey


private val Context.dataStore by preferencesDataStore(
    name = "user_settings"
)

object UserPreferences {

    private val UNITS = stringPreferencesKey("units")
    private val LANGUAGE = stringPreferencesKey("language")
    private val TRACKING_ENABLED = booleanPreferencesKey("tracking_enabled")
    private val DARK_THEME = booleanPreferencesKey("dark_theme")
    private val BOOKMARKED_TRAILS = stringSetPreferencesKey("bookmarked_trails")
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
    fun getDarkTheme(context: Context): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[DARK_THEME] ?: false
        }
    }

    suspend fun saveDarkTheme(context: Context, enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_THEME] = enabled
        }
    }
    fun getBookmarkedTrails(context: Context): Flow<Set<String>> {
        return context.dataStore.data.map { preferences ->
            preferences[BOOKMARKED_TRAILS] ?: emptySet()
        }
    }

    suspend fun toggleBookmark(context: Context, trailId: String) {
        context.dataStore.edit { preferences ->
            val currentBookmarks = preferences[BOOKMARKED_TRAILS] ?: emptySet()

            preferences[BOOKMARKED_TRAILS] =
                if (trailId in currentBookmarks) {
                    currentBookmarks - trailId
                } else {
                    currentBookmarks + trailId
                }
        }
    }
}