package com.example.lexilearn.data.quiz.model

data class QuizAnswerResponse(
    val code: Int,
    val message: String,
    val data: QuizAnswerData
)

data class QuizAnswerData(
    val answerId: Int,
    val score: String
)