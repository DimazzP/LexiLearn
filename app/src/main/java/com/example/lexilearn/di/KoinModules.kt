package com.example.lexilearn.di

import com.example.lexilearn.di.feature.authModule

val koinModule = listOf(
    networkModule,
    preferenceModule,
    authModule
)