package com.example.lexilearn.domain.screening.mapper

import com.example.lexilearn.data.screening.model.ScreeningAnswerResult
import com.example.lexilearn.domain.screening.model.ScreeningAnswerModel

fun ScreeningAnswerResult.toDomain() = ScreeningAnswerModel(
    answerId = answerId,
    score = score
)

