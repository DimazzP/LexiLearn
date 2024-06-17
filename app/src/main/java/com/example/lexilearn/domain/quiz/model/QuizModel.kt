package com.example.lexilearn.domain.quiz.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizModel(
    val id: Int,
    val type: String,
    val question: String,
    val answerList: List<String>,
    val answerKey: String,
    val answerType: String,
) : Parcelable