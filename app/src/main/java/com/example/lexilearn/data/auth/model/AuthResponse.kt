package com.example.lexilearn.data.auth.model

data class AuthResponse(
	val code: Int,
	val data: AuthData?,
	val message: String
)

data class User(
	val createdAt: String,
	val name: String,
	val photo: String,
	val id: Int,
	val email: String
)

data class AuthData(
	val accessToken: String,
	val user: User
)