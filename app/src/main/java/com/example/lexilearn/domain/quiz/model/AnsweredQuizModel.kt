package com.example.lexilearn.domain.quiz.model

data class AnsweredQuizModel(
    val questionId: Int,
    val answer: List<String>
)