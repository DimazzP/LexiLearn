package com.example.lexilearn.domain.quiz

import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.data.quiz.QuizRepository
import com.example.lexilearn.data.quiz.model.QuizAnswerResponse
import com.example.lexilearn.domain.quiz.model.AnsweredQuizModel
import com.example.lexilearn.domain.quiz.model.QuizModel
import com.example.lexilearn.domain.quiz.model.QuizResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class QuizInteractor(private val quizRepository: QuizRepository) : QuizUseCase {
    override fun getQuiz(): Flow<ApiResponse<List<QuizModel>>> {
        return quizRepository.getQuiz().flowOn(Dispatchers.IO)
    }

    override fun sendAnswers(answers: List<AnsweredQuizModel>): Flow<ApiResponse<QuizResultModel>> {
        return quizRepository.sendAnswers(answers).flowOn(Dispatchers.IO)
    }
}