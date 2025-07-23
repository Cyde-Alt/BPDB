package com.example.bpbdapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("members")
    fun getMembers(): Call<List<Member>>

    @GET("tasks")
    fun getTasks(): Call<List<Task>>

    @GET("memos")
    fun getMemos(): Call<List<Memo>>

    @GET("news")
    fun getNews(): Call<List<News>>
}
