package com.example.lexilearn.di

import com.example.lexilearn.di.feature.alphabetFeature
import com.example.lexilearn.di.feature.authModule
import com.example.lexilearn.di.feature.quizFeature
import com.example.lexilearn.di.feature.screeningModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

val koinModule = listOf(
    networkModule,
    preferenceModule,
    authModule,
    screeningModule,
    quizFeature,
    alphabetFeature
)

object KoinModules {
    fun reloadModule() {
        unloadKoinModules(koinModule)
        loadKoinModules(koinModule)
    }
}