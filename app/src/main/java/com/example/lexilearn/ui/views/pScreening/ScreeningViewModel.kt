package com.example.lexilearn.ui.views.pScreening

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.lexilearn.domain.models.ModelScreening

class ScreeningViewModel : ViewModel() {
    val itemQuestion =
        mutableStateListOf(
            ModelScreening(1, "Is there a family history of learning disorders?", 0),
            ModelScreening(1, "Is there a family history of learning disorders?", 0),
            ModelScreening(1, "Is there a family history of learning disorders?", 0),
            ModelScreening(1, "Is there a family history of learning disorders?", 0),
            ModelScreening(1, "Is there a family history of learning disorders?", 0),
            ModelScreening(1, "Is there a family history of learning disorders?", 0),
        )
}