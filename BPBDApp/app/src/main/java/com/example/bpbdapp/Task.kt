package com.example.bpbdapp

data class Task(
    val id: String,
    val title: String,
    val location: String,
    val disaster_type: String,
    val status: String,
    val members: List<Member> = emptyList()
)
