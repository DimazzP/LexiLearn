package com.example.lexilearn.ui.views.pLogin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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

class LoginViewModel(private val useCase: AuthUseCase) : ViewModel() {
    var email by mutableStateOf(TextFieldValue(""))
    var password by mutableStateOf(TextFieldValue(""))
    var showLoading by mutableStateOf(false)

    private val _loginState = MutableLiveData<ApiResponse<User>>(null)
    val loginState: LiveData<ApiResponse<User>> = _loginState

    fun login() {
        viewModelScope.launch {
            useCase.login(email.text, password.text)
                .collect {
                    _loginState.value = it
                }
        }
    }
}