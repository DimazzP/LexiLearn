package com.example.lexilearn.data.screening.remote

import com.example.lexilearn.data.screening.dto.ScreeningAnswerRequest
import com.example.lexilearn.data.screening.model.ScreeningAnswerResponse
import com.example.lexilearn.data.screening.model.ScreeningResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ScreeningService {
    @GET("screening/questions")
    suspend fun getQuestions(): ScreeningResponse

    @POST("screening/answers")
    suspend fun submitAnswers(
        @Body request: ScreeningAnswerRequest
    ): ScreeningAnswerResponse
}