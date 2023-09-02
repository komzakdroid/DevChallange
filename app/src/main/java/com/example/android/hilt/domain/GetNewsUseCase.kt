package com.example.android.hilt.domain

import com.example.android.hilt.data.model.ArticleDTO
import com.example.android.hilt.data.repository.NewsRepository
import com.example.android.hilt.util.DispatchersProvider
import com.example.android.hilt.util.Result
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
    private val dispatcher: DispatchersProvider
) {
    suspend operator fun invoke(search: String): Result<ArticleDTO> {
        return withContext(dispatcher.io) {
            val response = repository.getArticles(search)

            if (response.isSuccessful) {
                Result.Success(response.body())
            } else {
                Result.Error(RuntimeException(""), "Something went wrong!")
            }
        }
    }
}