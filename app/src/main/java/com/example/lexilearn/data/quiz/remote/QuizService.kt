package com.example.lexilearn.data.quiz.remote

import com.example.lexilearn.data.quiz.model.QuizResponse
import retrofit2.http.GET

interface QuizService {
    @GET("quiz/questions")
    suspend fun getQuiz(): QuizResponse
}