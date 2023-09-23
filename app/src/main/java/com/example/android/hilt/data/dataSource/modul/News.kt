package com.example.android.hilt.data.dataSource.modul

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.hilt.data.model.Article
import java.io.Serializable

@Entity(tableName = News.TABLE_NAME)
data class News(
    @PrimaryKey(autoGenerate = true) val newsId: Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable {
    companion object {
        const val TABLE_NAME = "news"
    }
}

fun Article.mapToNews() = News(
    author = this.author,
    content = this.content,
    description = this.description,
    publishedAt = this.publishedAt,
    title = this.title,
    url = this.url,
    urlToImage = this.urlToImage
)