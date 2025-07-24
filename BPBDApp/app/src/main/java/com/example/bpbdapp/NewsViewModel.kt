package com.example.bpbdapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NewsRepository
    val allNews: LiveData<List<NewsEntity>>

    init {
        val newsDao = AppDatabase.getDatabase(application).newsDao()
        repository = NewsRepository(newsDao, ApiClient.instance)
        allNews = repository.allNews
        refreshDataFromServer()
    }

    private fun refreshDataFromServer() {
        viewModelScope.launch {
            repository.refreshNews()
        }
    }
}
