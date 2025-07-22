package com.example.bpbdapp

data class Task(
    val id: String,
    val title: String,
    val location: String,
    val disasterType: String,
    val assignedMemberIds: List<String>,
    val status: String // "diterima", "dikerjakan", "selesai"
)
