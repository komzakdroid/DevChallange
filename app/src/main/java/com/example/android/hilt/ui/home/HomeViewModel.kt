package com.example.android.hilt.ui.home

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

class HomeViewModel @Inject constructor(
    private val useCase: GetNewsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun initData(search: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, isEmpty = false) }

            when (val result = useCase.invoke(search)) {
                is Result.Success -> {

                    _uiState.update {
                        it.copy(
                            loading = false,
                            data = (result.data?.articles?.filter { article ->
                                article?.title != null && article.title != ""
                            }) ?: emptyList(),
                            isEmpty = result.data?.articles?.isEmpty() ?: false,
                        )
                    }
                }

                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = "Something went wrong!",
                            data = null,
                            isEmpty = false
                        )
                    }
                }
            }
        }
    }
}

