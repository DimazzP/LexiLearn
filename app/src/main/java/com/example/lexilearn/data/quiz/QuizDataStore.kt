package com.example.lexilearn.data.quiz

import android.content.Context
import android.util.Log
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.data.quiz.dto.QuizAnswerItemRequest
import com.example.lexilearn.data.quiz.dto.QuizAnswerRequest
import com.example.lexilearn.data.quiz.model.QuizAnswerResponse
import com.example.lexilearn.data.quiz.remote.QuizService
import com.example.lexilearn.domain.quiz.mapper.toDomain
import com.example.lexilearn.domain.quiz.model.AnsweredQuizModel
import com.example.lexilearn.domain.quiz.model.QuizModel
import com.example.lexilearn.domain.quiz.model.QuizResultModel
import com.example.lexilearn.util.toApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuizDataStore(private val service: QuizService, private val context: Context) : QuizRepository {
    override fun getQuiz(): Flow<ApiResponse<List<QuizModel>>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = service.getQuiz()
            val quiz = response.data.toDomain()
            emit(ApiResponse.Success(quiz))
        } catch (e: Exception) {
            emit(e.toApiResponse(context))
        }
    }

    override fun sendAnswers(answers: List<AnsweredQuizModel>): Flow<ApiResponse<QuizResultModel>> = flow {
        try {
            emit(ApiResponse.Loading)
            val answersRequest = answers.map {
                val answer = it.answer.joinToString(",")
                QuizAnswerItemRequest(it.questionId, answer)
            }
            val payload = QuizAnswerRequest(answersRequest)
            val response = service.sendAnswers(payload)
            val data = response.data.toDomain()

            emit(ApiResponse.Success(data))
        } catch (e: Exception) {
            emit(e.toApiResponse(context))
        }
    }
}