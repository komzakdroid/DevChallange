package com.example.android.hilt.ui.article

import com.example.android.hilt.data.model.Article

data class ArticleScreenUiState(
    val loading: Boolean = false,
    val isEmpty: Boolean = false,
    val error: String? = null,
    val data: List<Article?>? = emptyList()
)