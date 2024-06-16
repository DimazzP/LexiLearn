package com.example.lexilearn.data.screening

import android.content.Context
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.data.screening.dto.ScreeningAnswerItems
import com.example.lexilearn.data.screening.dto.ScreeningAnswerRequest
import com.example.lexilearn.data.screening.remote.ScreeningService
import com.example.lexilearn.domain.screening.mapper.toDomain
import com.example.lexilearn.domain.screening.model.ScreeningAnswerModel
import com.example.lexilearn.domain.screening.model.ScreeningQuestionModel
import com.example.lexilearn.util.toApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ScreeningDataStore(
    private val screeningService: ScreeningService,
    private val context: Context
) :
    ScreeningRepository {
    override fun getQuestions(): Flow<ApiResponse<List<ScreeningQuestionModel>>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = screeningService.getQuestions()
            val questions = response.data.toDomain()

            emit(ApiResponse.Success(questions))
        } catch (e: Exception) {
            emit(e.toApiResponse(context))
        }
    }

    override fun sendAnswers(answers: List<ScreeningQuestionModel>): Flow<ApiResponse<ScreeningAnswerModel>> = flow {
        try {
            emit(ApiResponse.Loading)
            val payloadItems = answers.map { ScreeningAnswerItems(it.id, it.answer) }
            val payload = ScreeningAnswerRequest(payloadItems)
            val response = screeningService.submitAnswers(payload)
            val answers = response.data.toDomain()

            emit(ApiResponse.Success(answers))
        } catch (e: Exception) {
            emit(e.toApiResponse(context))
        }
    }
}