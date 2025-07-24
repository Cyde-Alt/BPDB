package com.example.bpbdapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memos")
data class MemoEntity(
    @PrimaryKey val id: String,
    val title: String,
    val message: String,
    val status: String,
    val createdAt: Long
)
