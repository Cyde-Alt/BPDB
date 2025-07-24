package com.example.bpbdapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TasksViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getTasks() {
        _isLoading.value = true
        ApiClient.instance.getTasks().enqueue(object : Callback<List<Task>> {
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _tasks.value = response.body()
                } else {
                    _errorMessage.value = "Gagal memuat tugas: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = "Gagal memuat tugas: ${t.message}"
            }
        })
    }
}
