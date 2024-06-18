package com.example.lexilearn.data.auth.remote

import com.example.lexilearn.data.auth.dto.LoginRequest
import com.example.lexilearn.data.auth.dto.RegisterRequest
import com.example.lexilearn.data.auth.model.AuthResponse
import com.example.lexilearn.data.auth.model.InspectResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
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

    @GET("/inspect")
    suspend fun inspect(): InspectResponse
}