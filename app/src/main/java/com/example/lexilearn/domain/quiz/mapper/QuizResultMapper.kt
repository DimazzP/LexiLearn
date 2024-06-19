package com.example.lexilearn.domain.quiz.mapper

import com.example.lexilearn.data.quiz.model.QuizAnswerData
import com.example.lexilearn.domain.quiz.model.QuizResultModel

fun QuizAnswerData.toDomain(): QuizResultModel {
    return QuizResultModel(
        answerId = answerId,
        score = score
    )
}