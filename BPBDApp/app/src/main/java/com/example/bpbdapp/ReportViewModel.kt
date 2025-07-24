package com.example.bpbdapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportViewModel : ViewModel() {

    private val _isSubmitting = MutableLiveData<Boolean>()
    val isSubmitting: LiveData<Boolean> = _isSubmitting

    private val _submissionStatus = MutableLiveData<Boolean>()
    val submissionStatus: LiveData<Boolean> = _submissionStatus

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun submitReport(report: Report) {
        _isSubmitting.value = true
        ApiClient.instance.submitReport(report).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                _isSubmitting.value = false
                if (response.isSuccessful) {
                    _submissionStatus.value = true
                } else {
                    _errorMessage.value = "Gagal mengirim laporan: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                _isSubmitting.value = false
                _errorMessage.value = "Gagal mengirim laporan: ${t.message}"
            }
        })
    }
}
