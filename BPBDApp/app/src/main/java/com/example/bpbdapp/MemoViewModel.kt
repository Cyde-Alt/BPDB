package com.example.bpbdapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MemoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MemoRepository
    val allMemos: LiveData<List<MemoEntity>>

    init {
        val memoDao = AppDatabase.getDatabase(application).memoDao()
        repository = MemoRepository(memoDao, ApiClient.instance)
        allMemos = repository.allMemos
        refreshDataFromServer()
    }

    private fun refreshDataFromServer() {
        viewModelScope.launch {
            repository.refreshMemos()
        }
    }
}
