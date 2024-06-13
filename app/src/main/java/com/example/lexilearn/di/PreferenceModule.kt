package com.example.lexilearn.di

import com.example.lexilearn.util.PreferenceManager
import org.koin.dsl.module

val preferenceModule = module {
    single { PreferenceManager(get()) }
}