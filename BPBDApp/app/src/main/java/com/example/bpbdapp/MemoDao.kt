package com.example.bpbdapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MemoDao {

    @Query("SELECT * FROM memos ORDER BY createdAt DESC")
    fun getAllMemos(): LiveData<List<MemoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(memos: List<MemoEntity>)

    @Query("DELETE FROM memos")
    suspend fun deleteAll()
}
