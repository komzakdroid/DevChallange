package com.example.android.hilt.domain

import com.example.android.hilt.data.model.ArticleDTO
import com.example.android.hilt.data.repository.NewsRepository
import com.example.android.hilt.util.DispatchersProvider
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcher: DispatchersProvider
) {
    suspend operator fun invoke(search: String): Result<ArticleDTO> {
        return withContext(dispatcher.io){
            when(val result = newsRepository.getArticles(search)){
                is Result
            }
        }
    }
}
