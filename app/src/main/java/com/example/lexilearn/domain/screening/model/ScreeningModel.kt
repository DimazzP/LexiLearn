package com.example.lexilearn.domain.screening.model

data class ScreeningQuestionModel(
    val id: Int,
    val question: String,
    var answer: String
)