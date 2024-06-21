package com.example.lexilearn.domain.quiz

import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.quiz.model.AnsweredQuizModel
import com.example.lexilearn.domain.quiz.model.QuizModel
import com.example.lexilearn.domain.quiz.model.QuizResultModel
import kotlinx.coroutines.flow.Flow

interface QuizUseCase {
    fun getQuiz(): Flow<ApiResponse<List<QuizModel>>>
    fun sendAnswers(answers: List<AnsweredQuizModel>): Flow<ApiResponse<QuizResultModel>>
}