package com.example.lexilearn.ui.views.pSplashcreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.auth.AuthUseCase
import com.example.lexilearn.domain.auth.model.User
import kotlinx.coroutines.launch

class SplashViewModel(private val useCase: AuthUseCase) : ViewModel() {
    private val _state = MutableLiveData<ApiResponse<User>>(null)
    val state: LiveData<ApiResponse<User>> = _state

    fun inspect() {
        viewModelScope.launch {
            useCase.inspect().collect {
                _state.value = it
            }
        }
    }
}