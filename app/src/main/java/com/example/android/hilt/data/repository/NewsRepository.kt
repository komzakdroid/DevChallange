package com.example.android.hilt.data.repository

import com.example.android.hilt.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getArticles(search: String) = apiService.getNews(search)
}