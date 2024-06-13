package com.example.lexilearn.data.auth.dto

data class LoginRequest (
    val email: String,
    val password: String
)

data class RegisterRequest (
    val name: String,
    val email: String,
    val password: String
)