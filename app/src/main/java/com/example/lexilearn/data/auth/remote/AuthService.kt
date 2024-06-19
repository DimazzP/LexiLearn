package com.example.lexilearn.data.auth.remote

import com.example.lexilearn.data.auth.dto.LoginRequest
import com.example.lexilearn.data.auth.dto.PasswordRequest
import com.example.lexilearn.data.auth.dto.ProfileRequest
import com.example.lexilearn.data.auth.dto.RegisterRequest
import com.example.lexilearn.data.auth.model.AuthResponse
import com.example.lexilearn.data.auth.model.InspectResponse
import com.example.lexilearn.domain.auth.model.ProfileResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthService {
    @POST("/login")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse

    @POST("/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): AuthResponse

    @PATCH("/profile")
    suspend fun updateProfile(
        @Body request: ProfileRequest
    ): ProfileResponse

    @PATCH("/change-password")
    suspend fun changePassword(
        @Body request: PasswordRequest
    ): AuthResponse

    @GET("/profile")
    suspend fun getProfile(): AuthResponse

    @GET("/inspect")
    suspend fun inspect(): InspectResponse
}