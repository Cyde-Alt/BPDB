package com.example.bpbdapp

import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(private val newsDao: NewsDao, private val apiService: ApiService) {

    val allNews: LiveData<List<NewsEntity>> = newsDao.getAllNews()

    suspend fun refreshNews() {
        try {
            val response = apiService.getNews().execute() // Synchronous call
            if (response.isSuccessful) {
                val newsList = response.body()
                if (newsList != null) {
                    val newsEntities = newsList.map { news ->
                        NewsEntity(
                            id = news.id,
                            title = news.title,
                            content = news.content,
                            imageUrl = news.imageUrl,
                            createdAt = news.createdAt
                        )
                    }
                    newsDao.deleteAll()
                    newsDao.insertAll(newsEntities)
                }
            } else {
                Log.e("NewsRepository", "Failed to refresh news: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("NewsRepository", "Exception refreshing news", e)
        }
    }
}
