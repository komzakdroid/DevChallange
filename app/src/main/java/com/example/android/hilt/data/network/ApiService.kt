package com.example.android.hilt.data.network

import com.example.android.hilt.data.model.ArticleDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/everything")
    suspend fun getNews(
        @Query("q") search: String
    ): ArticleDTO
}