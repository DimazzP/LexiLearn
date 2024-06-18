package com.example.lexilearn.domain.screening

import com.example.lexilearn.data.auth.AuthRepository
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.data.screening.ScreeningRepository
import com.example.lexilearn.data.screening.dto.ScreeningAnswerRequest
import com.example.lexilearn.domain.auth.model.User
import com.example.lexilearn.domain.screening.model.ScreeningAnswerModel
import com.example.lexilearn.domain.screening.model.ScreeningQuestionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class ScreeningInteractor(private val screeningRepository: ScreeningRepository) : ScreeningUseCase {
    override fun getQuestions(): Flow<ApiResponse<List<ScreeningQuestionModel>>> {
        return screeningRepository.getQuestions().flowOn(Dispatchers.IO)
    }

    override fun sendAnswers(answers: List<ScreeningQuestionModel>): Flow<ApiResponse<ScreeningAnswerModel>> {
        return screeningRepository.sendAnswers(answers).flowOn(Dispatchers.IO)
    }
}