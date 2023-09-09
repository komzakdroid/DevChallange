package com.example.android.hilt.ui.article

import com.example.android.hilt.domain.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val useCase: GetNewsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ArticleScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun initData() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, isEmpty = false) }

        }
    }
}