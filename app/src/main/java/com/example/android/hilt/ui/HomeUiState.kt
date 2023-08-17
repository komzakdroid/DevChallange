package com.example.android.hilt.ui

import com.example.android.hilt.data.model.Article

// Represents different states for the Home screen
sealed class HomeUiState {
    data class Success(val news: List<Article>) : HomeUiState()
    data class Loading(val isLoading:Boolean) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}