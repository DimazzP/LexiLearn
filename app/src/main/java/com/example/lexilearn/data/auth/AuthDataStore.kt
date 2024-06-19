package com.example.lexilearn.data.auth

import android.content.Context
import android.util.Log
import com.example.lexilearn.R
import com.example.lexilearn.data.auth.dto.LoginRequest
import com.example.lexilearn.data.auth.dto.PasswordRequest
import com.example.lexilearn.data.auth.dto.ProfileRequest
import com.example.lexilearn.data.auth.dto.RegisterRequest
import com.example.lexilearn.data.auth.model.AuthResponse
import com.example.lexilearn.data.auth.remote.AuthService
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.di.KoinModules
import com.example.lexilearn.domain.auth.mapper.toDomain
import com.example.lexilearn.domain.auth.model.User
import com.example.lexilearn.util.PreferenceManager
import com.example.lexilearn.util.toApiResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class AuthDataStore(
    private val authService: AuthService,
    private val preferenceManager: PreferenceManager,
    private val context: Context
) : AuthRepository {
    override fun login(email: String, password: String): Flow<ApiResponse<User>> = flow {
        try {
            emit(ApiResponse.Loading)
            val payload = LoginRequest(email, password)
            val authData = authService.login(payload)

            val userData = authData.data
            val user = userData?.user?.toDomain()

            preferenceManager.setLoginPref(userData!!)

            KoinModules.reloadModule()

            emit(ApiResponse.Success(user!!))
        } catch (e: Exception) {
            emit(e.toApiResponse(context))
        }
    }

    override fun register(name: String, email: String, password: String): Flow<ApiResponse<String>> = flow {
        try {
            emit(ApiResponse.Loading)
            val payload = RegisterRequest(name, email, password)
            val authData = authService.register(payload)

            emit(ApiResponse.Success(authData.message))
        } catch(e: Exception) {
            emit(e.toApiResponse(context))
        }
    }

    override fun updateProfile(name: String, confirm_password: String): Flow<ApiResponse<User>> = flow{
        try {
            emit(ApiResponse.Loading)
            val payload = ProfileRequest(name, confirm_password)
            val authData = authService.updateProfile(payload)

            val userData = authData.data
            val user = userData

            preferenceManager.setUserPref(user!!)

            KoinModules.reloadModule()

            emit(ApiResponse.Success(user))
        } catch(e: Exception) {
            emit(e.toApiResponse(context))
        }
    }

    override fun updatePassword(
        current_password: String,
        new_password: String,
        confirm_password: String
    ): Flow<ApiResponse<String>> = flow{
        try {
            emit(ApiResponse.Loading)
            val payload = PasswordRequest(current_password, new_password, confirm_password)
            val authData = authService.changePassword(payload)
            emit(ApiResponse.Success(authData.message))
        } catch(e: Exception) {
            emit(e.toApiResponse(context))
        }
    }

    override fun inspect(): Flow<ApiResponse<User>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = authService.inspect()
            val user = response.data.toDomain()

            preferenceManager.setUserPref(user)

            KoinModules.reloadModule()

            emit(ApiResponse.Success(user))
        } catch (e: Exception) {
            emit(e.toApiResponse(context))
        }
    }
}