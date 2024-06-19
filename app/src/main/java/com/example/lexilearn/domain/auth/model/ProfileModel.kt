package com.example.lexilearn.domain.auth.model

data class ProfileResponse(
    val code: Int,
    val data: User?,
    val message: String
)