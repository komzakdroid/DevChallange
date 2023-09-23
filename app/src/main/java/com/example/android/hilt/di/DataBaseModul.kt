package com.example.android.hilt.di

import android.app.Application
import com.example.android.hilt.data.dataSource.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = NewsDataBase.getDatabase(application)

    @Singleton
    @Provides
    fun provideNewsDao(database: NewsDataBase) =
        database.getNewsDao()

}