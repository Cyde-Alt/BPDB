package com.example.bpbdapp

import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @GET("members")
    fun getMembers(): Call<List<Member>>

    @GET("tasks")
    fun getTasks(): Call<List<Task>>

    @GET("memos")
    fun getMemos(): Call<List<Memo>>

    @GET("news")
    fun getNews(): Call<List<News>>

    @POST("reports")
    fun submitReport(@Body report: Report): Call<Void>
}

data class Report(
    val title: String,
    val description: String,
    val imageUrl: String?,
    val timestamp: Long
)
