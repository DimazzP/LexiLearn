package com.example.lexilearn.domain.quiz

import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.quiz.model.QuizModel
import kotlinx.coroutines.flow.Flow

interface QuizUseCase {
    fun getQuiz(): Flow<ApiResponse<List<QuizModel>>>
}