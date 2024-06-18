package com.example.lexilearn.data.auth

import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.auth.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String, password: String): Flow<ApiResponse<User>>
    fun register(name: String, email: String, password: String): Flow<ApiResponse<String>>
    fun updateProfile(name: String, confirm_password: String): Flow<ApiResponse<User>>
    fun updatePassword(
        current_password: String,
        new_password: String,
        confirm_password: String
    ): Flow<ApiResponse<String>>

    fun inspect(): Flow<ApiResponse<User>>
}