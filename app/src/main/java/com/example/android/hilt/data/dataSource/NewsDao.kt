package com.example.android.hilt.data.dataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.hilt.data.dataSource.modul.News

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNews(news: List<News?>)

    @Query("SELECT * FROM news")
    fun getAllNews(): List<News>
}