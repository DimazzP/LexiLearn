package com.example.lexilearn.data.screening.dto

data class ScreeningAnswerRequest(
    val answers: List<ScreeningAnswerItems>
)

data class ScreeningAnswerItems(
    val questionId: Int,
    val answer: String
)