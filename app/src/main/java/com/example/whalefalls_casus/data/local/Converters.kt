package com.example.whalefalls_casus.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromListToString(list: List<String>?): String { // Added 'fun'
        return gson.toJson(list ?: emptyList<String>())
    }

    @TypeConverter
    fun fromStringToList(data: String?): List<String> { // Added 'fun'
        if (data.isNullOrEmpty()) return emptyList()
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, listType)
    }
}