package com.example.lexilearn.domain.screening.mapper

import com.example.lexilearn.data.screening.model.ScreeningQuestionItems
import com.example.lexilearn.domain.screening.model.ScreeningQuestionModel

fun List<ScreeningQuestionItems>.toDomain(): List<ScreeningQuestionModel> {
    return this.map {
        it.toDomain()
    }
}

fun ScreeningQuestionItems.toDomain(): ScreeningQuestionModel {
    return ScreeningQuestionModel(
        id = this.id,
        question = this.question,
        answer = ""
    )
}