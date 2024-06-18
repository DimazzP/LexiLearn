package com.example.lexilearn.data.screening

import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.data.screening.dto.ScreeningAnswerRequest
import com.example.lexilearn.domain.screening.model.ScreeningAnswerModel
import com.example.lexilearn.domain.screening.model.ScreeningQuestionModel
import kotlinx.coroutines.flow.Flow

interface ScreeningRepository {
    fun getQuestions(): Flow<ApiResponse<List<ScreeningQuestionModel>>>
    fun sendAnswers(answers: List<ScreeningQuestionModel>): Flow<ApiResponse<ScreeningAnswerModel>>
}