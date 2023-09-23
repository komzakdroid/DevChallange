package com.example.android.hilt.domain

import com.example.android.hilt.data.dataSource.NewsDao
import com.example.android.hilt.data.dataSource.modul.News
import com.example.android.hilt.data.dataSource.modul.mapToNews
import com.example.android.hilt.data.network.NewsResource
import com.example.android.hilt.data.repository.NewsRepository
import com.example.android.hilt.utils.DispatchersProvider
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
    private val newsDao: NewsDao,
    private val dispatcher: DispatchersProvider
) {
    suspend operator fun invoke(search: String): NewsResource<List<News>> {
        return withContext(dispatcher.io) {

            NewsResource.Loading(true)

            val response = repository.getArticles(search)

            if (response.isSuccessful) {
                try {
                    /** insert database */
                    response.body()?.articles?.map { article ->
                        article?.mapToNews()
                    }?.let { news -> newsDao.addNews(news) }

                    /** get database */
                    val news = newsDao.getAllNews()
                    NewsResource.Success(news)

                } catch (e: Exception) {
                    NewsResource.Error(e.message.toString())
                }

            } else {
                NewsResource.Error("Something went wrong!")
            }
        }
    }
}