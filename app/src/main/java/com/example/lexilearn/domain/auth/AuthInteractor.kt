package com.example.lexilearn.domain.auth

import com.example.lexilearn.data.auth.AuthRepository
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.auth.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class AuthInteractor(private val repository: AuthRepository) : AuthUseCase {
    override fun login(email: String, password: String): Flow<ApiResponse<User>> {
        return repository.login(email, password).flowOn(Dispatchers.IO)
    }

    override fun register(name: String, email: String, password: String): Flow<ApiResponse<String>> {
        return repository.register(name, email, password).flowOn(Dispatchers.IO)
    }

    override fun inspect(): Flow<ApiResponse<User>> {
        return repository.inspect().flowOn(Dispatchers.IO)
    }
}