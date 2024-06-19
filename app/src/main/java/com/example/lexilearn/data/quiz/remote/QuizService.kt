package com.example.lexilearn.data.quiz.remote

import com.example.lexilearn.data.quiz.dto.QuizAnswerRequest
import com.example.lexilearn.data.quiz.model.QuizAnswerResponse
import com.example.lexilearn.data.quiz.model.QuizResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuizService {
    @GET("quiz/questions")
    suspend fun getQuiz(): QuizResponse

    @POST("quiz/answers")
    suspend fun sendAnswers(@Body request: QuizAnswerRequest): QuizAnswerResponse
}