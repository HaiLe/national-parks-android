package me.haile.nationalparks.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.haile.nationalparks.data.Activity

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromActivityList(value: List<Activity>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toActivityList(value: String): List<Activity> {
        val objectType = object : TypeToken<List<Activity>>() {}.type
        return gson.fromJson(value, objectType)
    }

    // Implement similar converters for other complex types
}
