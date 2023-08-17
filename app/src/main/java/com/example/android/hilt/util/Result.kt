package com.example.android.hilt.util

import com.example.android.hilt.data.model.ArticleDTO

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: ArticleDTO?) : Result<T>()
    data class Error(
        val exception: Exception,
        val errorMessage: String,
    ) : Result<Nothing>()

}