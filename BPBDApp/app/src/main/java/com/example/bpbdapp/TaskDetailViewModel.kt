package com.example.bpbdapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskDetailViewModel : ViewModel() {

    private val _task = MutableLiveData<Task>()
    val task: LiveData<Task> = _task

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getTaskDetails(taskId: String) {
        _isLoading.value = true
        ApiClient.instance.getTask(taskId).enqueue(object : Callback<Task> {
            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _task.value = response.body()
                } else {
                    _errorMessage.value = "Gagal memuat detail tugas: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<Task>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = "Gagal memuat detail tugas: ${t.message}"
            }
        })
    }
}
