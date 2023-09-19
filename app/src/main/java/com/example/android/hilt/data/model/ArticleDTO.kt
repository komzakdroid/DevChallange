package com.example.android.hilt.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleDTO(
    @SerializedName("articles")
    val articles: List<Article?>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
) : Serializable