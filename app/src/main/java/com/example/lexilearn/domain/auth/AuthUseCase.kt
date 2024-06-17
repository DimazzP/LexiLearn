package com.example.lexilearn.domain.auth

import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.auth.model.User
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    fun login(email: String, password: String): Flow<ApiResponse<User>>
    fun register(name: String, email: String, password: String): Flow<ApiResponse<String>>
    fun inspect(): Flow<ApiResponse<User>>
}