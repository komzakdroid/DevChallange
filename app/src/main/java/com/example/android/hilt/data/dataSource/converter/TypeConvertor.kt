package com.example.android.hilt.data.dataSource.converter

import androidx.room.TypeConverter
import com.example.android.hilt.data.dataSource.modul.News
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class NewsTypeConvertor {
    @TypeConverter
    fun fromStringToNewsList(data: String): List<News> {
        val listType = object : TypeToken<ArrayList<News>>() {}.type
        return GsonBuilder().create().fromJson(data, listType)
    }

    @TypeConverter
    fun fromNewsListTypeToString(breed: ArrayList<News>): String {
        return GsonBuilder().create().toJson(breed)
    }

}