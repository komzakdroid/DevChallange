package com.example.android.hilt.data.dataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.hilt.data.dataSource.converter.NewsTypeConvertor
import com.example.android.hilt.data.dataSource.modul.News


@Database(
    entities = [News::class],
    version = 1,
    exportSchema = true
)

@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
    companion object {
        @Volatile
        private var INSTANCE: NewsDataBase? = null
        fun getDatabase(context: Context): NewsDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDataBase::class.java,
                    "news_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}