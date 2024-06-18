package com.example.lexilearn.ui.views.pQuiz.pWrite

import android.graphics.Bitmap
import android.graphics.Path
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import com.example.lexilearn.domain.models.ModelSpell

class WriteViewModel: ViewModel() {
    var pathReady: MutableState<Path?> = mutableStateOf(null)
    var canvasSize: MutableState<Size> = mutableStateOf(Size.Zero)
    var showLoading by mutableStateOf(false)


    // gunakan variabel ini untuk mengambil hasil gambar
    var bitmap: MutableState<Bitmap?> = mutableStateOf(null)

    val dataQuiz = mutableStateListOf(
        ModelSpell(1, false, "r ", showCard = false),
        ModelSpell(2, false, "i", showCard = false),
        ModelSpell(3, true, "?", showCard = false),
        ModelSpell(4, false, "e", showCard = false),
    )
}