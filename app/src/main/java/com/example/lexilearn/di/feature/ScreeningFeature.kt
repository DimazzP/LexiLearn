package com.example.lexilearn.di.feature

import com.example.lexilearn.data.screening.ScreeningDataStore
import com.example.lexilearn.data.screening.ScreeningRepository
import com.example.lexilearn.domain.screening.ScreeningInteractor
import com.example.lexilearn.domain.screening.ScreeningUseCase
import com.example.lexilearn.ui.views.pScreening.ScreeningViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val screeningModule = module {
    factory<ScreeningUseCase> { ScreeningInteractor(get()) }
    factory<ScreeningRepository> { ScreeningDataStore(get(), get()) }

    single { ScreeningDataStore(get(), get()) }
    single { ScreeningInteractor(get()) }

    viewModel { ScreeningViewModel(get()) }
}