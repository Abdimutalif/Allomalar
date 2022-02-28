package com.mac.allomalar.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String?>? {
        val listType = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromInt(value: String?): ArrayList<Int?>? {
        val listType = object : TypeToken<List<Int?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListInt(list: ArrayList<Int?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}