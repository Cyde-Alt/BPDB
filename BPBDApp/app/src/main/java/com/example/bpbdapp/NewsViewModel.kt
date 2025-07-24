package com.example.bpbdapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getNews() {
        _isLoading.value = true
        ApiClient.instance.getNews().enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _news.value = response.body()
                } else {
                    _errorMessage.value = "Gagal memuat berita: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = "Gagal memuat berita: ${t.message}"
            }
        })
    }
}
