package com.example.lexilearn.ui.views.pRegister

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.auth.AuthUseCase
import com.example.lexilearn.domain.auth.model.User
import kotlinx.coroutines.launch

class RegisterViewModel(private val useCase: AuthUseCase) : ViewModel() {
    var name by mutableStateOf(TextFieldValue(""))
    var email by mutableStateOf(TextFieldValue(""))
    var password by mutableStateOf(TextFieldValue(""))
    var showLoading by mutableStateOf(false)


    private val _registerState = MutableLiveData<ApiResponse<String>>(null)
    val registerState: LiveData<ApiResponse<String>> = _registerState

    fun register() {
        viewModelScope.launch {
            useCase.register(name.text, email.text, password.text)
                .collect {
                    _registerState.value = it
                }
        }
    }
}