package com.example.lexilearn.di

import com.example.lexilearn.di.feature.authModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

val koinModule = listOf(
    networkModule,
    preferenceModule,
    authModule
)

object KoinModules {
    fun reloadModule() {
        unloadKoinModules(koinModule)
        loadKoinModules(koinModule)
    }
}