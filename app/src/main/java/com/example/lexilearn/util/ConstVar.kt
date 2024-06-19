package com.example.lexilearn.util

import com.example.lexilearn.BuildConfig

object ConstVar {
    const val PREFS_NAME: String = "lexilearn_prefs"
    const val TOKEN_KEY: String = "access_token"
    const val BASE_URL: String = BuildConfig.baseUrl
    const val USER_NAME: String = "user_name"
    const val USER_EMAIL: String = "user_email"
    const val USER_PHOTO: String = "user_photo"
    const val USER_CREATED_AT: String = "user_created_at"

    const val SPEED_1: Float = 1.0f
    const val SPEED_0_5: Float = 0.5f
}