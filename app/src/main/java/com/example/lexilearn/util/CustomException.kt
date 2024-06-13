package com.example.lexilearn.util

import com.example.lexilearn.R
import com.example.lexilearn.data.auth.model.AuthResponse
import com.example.lexilearn.data.lib.ApiResponse
import com.google.gson.Gson
import retrofit2.HttpException

fun Exception.toApiResponse(): ApiResponse.Error {
    return when (this) {
        is HttpException -> {
            val errorMessage = Gson().fromJson(
                this.response()?.errorBody()?.string(),
                AuthResponse::class.java
            )
            ApiResponse.Error(errorMessage.message)
        }

        else -> {
            this.printStackTrace()
            ApiResponse.Error(R.string.unknown_error.toString())
        }
    }
}