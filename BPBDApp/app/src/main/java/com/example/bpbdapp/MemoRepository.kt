package com.example.bpbdapp

import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MemoRepository(private val memoDao: MemoDao, private val apiService: ApiService) {

    val allMemos: LiveData<List<MemoEntity>> = memoDao.getAllMemos()

    suspend fun refreshMemos() {
        try {
            val response = apiService.getMemos().execute() // Synchronous call
            if (response.isSuccessful) {
                val memoList = response.body()
                if (memoList != null) {
                    val memoEntities = memoList.map { memo ->
                        MemoEntity(
                            id = memo.id,
                            title = memo.title,
                            message = memo.message,
                            status = memo.status,
                            createdAt = memo.createdAt
                        )
                    }
                    memoDao.deleteAll()
                    memoDao.insertAll(memoEntities)
                }
            } else {
                Log.e("MemoRepository", "Failed to refresh memos: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("MemoRepository", "Exception refreshing memos", e)
        }
    }
}
