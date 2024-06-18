package com.example.lexilearn.domain.auth.model

data class User(
    val createdAt: String,
    val name: String,
    val photo: String,
    val id: Int,
    val email: String
)