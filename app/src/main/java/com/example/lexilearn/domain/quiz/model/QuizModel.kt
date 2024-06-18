package com.example.lexilearn.domain.quiz.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizModel(
    val choiceType: String,
    val question: String,
    val id: Int,
    val type: String,
    val choices: List<String>
) : Parcelable