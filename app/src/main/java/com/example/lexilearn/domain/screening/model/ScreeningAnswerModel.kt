package com.example.lexilearn.domain.screening.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScreeningAnswerModel(
    val answerId: Int,
    val score: Int
) : Parcelable