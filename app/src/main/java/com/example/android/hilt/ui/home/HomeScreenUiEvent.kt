package com.example.android.hilt.ui.home

sealed class HomeScreenUiEvent {
    data class SearchNews(val search: String) : HomeScreenUiEvent()
    object GetNews : HomeScreenUiEvent()

    data class CheckStatus(val status: Boolean) : HomeScreenUiEvent()
}