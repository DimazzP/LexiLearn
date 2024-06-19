package com.example.lexilearn.domain.quiz.mapper

import com.example.lexilearn.data.quiz.model.QuizData
import com.example.lexilearn.domain.quiz.model.QuizModel

fun List<QuizData>.toDomain(): List<QuizModel> {
    return map { it.toDomain() }
}

fun QuizData.toDomain(): QuizModel {
    return QuizModel(
        id = id,
        type = type,
        question = question,
        choiceType = choiceType,
        choices = choices,
    )
}