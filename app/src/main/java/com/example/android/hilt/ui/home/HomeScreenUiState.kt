package com.example.android.hilt.ui.home

import com.example.android.hilt.data.model.Article

// Represents different states for the Home screen
data class HomeScreenUiState(
    val loading: Boolean = false,
    val isEmpty: Boolean = false,
    val error: String? = null,
    val data: List<Article?>? = emptyList(),

    )

data class ConnectionStatus(
    val isOnline: Boolean? = null
)