package com.example.lexilearn.ui.views.pProfile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.auth.AuthUseCase
import com.example.lexilearn.domain.auth.model.User
import com.example.lexilearn.util.PreferenceManager
import kotlinx.coroutines.launch

class ProfileViewModel(private val useCase: AuthUseCase) : ViewModel() {
    var name by mutableStateOf(TextFieldValue(""))
    var password by mutableStateOf(TextFieldValue(""))
    var showLoading by mutableStateOf(false)
    var showProfile by mutableStateOf(true)
    var displayName by mutableStateOf("")

    var current_password by mutableStateOf(TextFieldValue(""))
    var new_password by mutableStateOf(TextFieldValue(""))
    var confirm_password by mutableStateOf(TextFieldValue(""))


    private val _updateProfileState = MutableLiveData<ApiResponse<User>>(null)
    val updateProfileState: MutableLiveData<ApiResponse<User>> = _updateProfileState

    fun updateProfile() {
        viewModelScope.launch {
            useCase.updateProfile(name.text, password.text)
                .collect {
                   _updateProfileState.value = it
                }
        }
    }

    private val _changePasswordState = MutableLiveData<ApiResponse<String>>(null)
    val changePasswordState: MutableLiveData<ApiResponse<String>> = _changePasswordState

    fun changePassword() {
        Log.d("checkValue", "coba panggil1")
        viewModelScope.launch {
            Log.d("checkValue", "coba panggil")
            useCase.updatePassword(current_password.text.trim(), new_password.text.trim(), new_password.text.trim())
                .collect {
                    _changePasswordState.value = it
                }
        }
    }
}