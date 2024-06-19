package com.example.lexilearn.data.quiz

import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.data.quiz.model.QuizAnswerResponse
import com.example.lexilearn.domain.quiz.model.AnsweredQuizModel
import com.example.lexilearn.domain.quiz.model.QuizModel
import com.example.lexilearn.domain.quiz.model.QuizResultModel
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuiz(): Flow<ApiResponse<List<QuizModel>>>
    fun sendAnswers(answers: List<AnsweredQuizModel>): Flow<ApiResponse<QuizResultModel>>
}