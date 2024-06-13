package com.example.lexilearn.di.feature

import com.example.lexilearn.data.auth.AuthDataStore
import com.example.lexilearn.data.auth.AuthRepository
import com.example.lexilearn.domain.auth.AuthInteractor
import com.example.lexilearn.domain.auth.AuthUseCase
import com.example.lexilearn.ui.views.pLogin.LoginViewModel
import com.example.lexilearn.ui.views.pRegister.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    factory<AuthUseCase> { AuthInteractor(get()) }
    factory<AuthRepository> { AuthDataStore(get(), get()) }

    single { AuthDataStore(get(), get()) }
    single { AuthInteractor(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
}