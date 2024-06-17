package com.example.lexilearn.di.feature

import com.example.lexilearn.data.quiz.QuizDataStore
import com.example.lexilearn.data.quiz.QuizRepository
import com.example.lexilearn.domain.quiz.QuizInteractor
import com.example.lexilearn.domain.quiz.QuizUseCase
import com.example.lexilearn.ui.views.pQuiz.QuizViewModel
import com.example.lexilearn.ui.views.pQuiz.pRead.ReadViewModel
import com.example.lexilearn.ui.views.pQuiz.pSpell.SpellViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val quizFeature = module {
    factory<QuizUseCase> { QuizInteractor(get()) }
    factory<QuizRepository> { QuizDataStore(get(), get()) }

    single { QuizDataStore(get(), get()) }
    single { QuizInteractor(get()) }

    viewModel { QuizViewModel(get()) }
    viewModel { ReadViewModel() }
    viewModel { SpellViewModel() }
}