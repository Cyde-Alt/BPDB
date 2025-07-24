package com.example.bpbdapp

data class News(
    val id: String,
    val title: String,
    val content: String,
    val imageUrl: String?,
    val createdAt: Long
)
