package com.example.lexilearn.ui.views.pScreening

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.screening.ScreeningUseCase
import com.example.lexilearn.domain.screening.model.ScreeningAnswerModel
import com.example.lexilearn.domain.screening.model.ScreeningQuestionModel
import kotlinx.coroutines.launch

class ScreeningViewModel(private val useCase: ScreeningUseCase) : ViewModel() {
    var showLoading = mutableStateOf(false)

    private val _screeningQuestions = MutableLiveData<ApiResponse<List<ScreeningQuestionModel>>>(null)
    val screeningQuestions: LiveData<ApiResponse<List<ScreeningQuestionModel>>> = _screeningQuestions

    private val _screeningAnswerResult = MutableLiveData<ApiResponse<ScreeningAnswerModel>>(null)
    val screeningAnswerResult: LiveData<ApiResponse<ScreeningAnswerModel>> = _screeningAnswerResult

    fun getQuestions() {
        viewModelScope.launch {
            useCase.getQuestions().collect {
                _screeningQuestions.value = it
            }
        }
    }

    fun sendAnswers() {
        viewModelScope.launch {
            val answers = (screeningQuestions.value as ApiResponse.Success).data

             useCase.sendAnswers(answers).collect {
                 _screeningAnswerResult.value = it
             }
        }
    }
}