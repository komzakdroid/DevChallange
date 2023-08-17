package com.example.android.hilt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.hilt.domain.GetNewsUseCase
import com.example.android.hilt.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class HomeViewModel @Inject constructor(private val useCase: GetNewsUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState.Loading(false))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            when (useCase.invoke("tashkent")) {
                is Result.Success -> {

                }

                is Result.Error -> {

                }
            }
        }
    }
}