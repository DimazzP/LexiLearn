package com.example.lexilearn.data.quiz

import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.quiz.model.QuizModel
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuiz(): Flow<ApiResponse<QuizModel>>
}