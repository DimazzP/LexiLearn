package com.example.lexilearn.data.quiz.dto

data class QuizAnswerRequest(
    val answers: List<QuizAnswerItemRequest>
)

data class QuizAnswerItemRequest(
    val questionId: Int,
    val answer: String
)