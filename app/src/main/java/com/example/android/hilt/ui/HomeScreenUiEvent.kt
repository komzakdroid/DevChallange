package com.example.android.hilt.ui

sealed class HomeScreenUiEvent {
    data class SearchNews(val search: String) : HomeScreenUiEvent()
    object GetNews : HomeScreenUiEvent()
}