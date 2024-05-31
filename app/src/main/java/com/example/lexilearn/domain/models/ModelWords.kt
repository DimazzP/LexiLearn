package com.example.lexilearn.domain.models

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect

data class ModelWords(
    var id: Int,
    var type: Boolean,
    var data: String,
    var emp: Int? = null,
    var hasContent: Boolean = false,
    var showCard: Boolean = false,
)
