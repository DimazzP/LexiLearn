package com.example.lexilearn.domain.models

data class ModelAnswerRead (
    var id: Int,
    var data: String,
    var showCard: Boolean = true,
)