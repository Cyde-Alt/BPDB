package com.example.bpbdapp

data class Member(
    val id: String,
    val name: String,
    val role: String,
    val status: String // "siaga", "bertugas", "non-aktif"
)
