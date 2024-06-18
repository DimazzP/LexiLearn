package com.example.lexilearn.di.feature

import com.example.lexilearn.ui.views.pAlphabet.AlphabetViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val alphabetFeature = module {
    viewModel { AlphabetViewModel() }
}