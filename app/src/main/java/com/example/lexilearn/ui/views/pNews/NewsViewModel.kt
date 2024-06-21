package com.example.lexilearn.ui.views.pNews

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lexilearn.data.news.NewsDataStore
import com.example.lexilearn.data.news.NewsRepository
import com.example.lexilearn.data.news.callback.NewsCallback
import com.example.lexilearn.data.news.model.NewsResponse
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {
    private val _newsState = MutableLiveData<NewsResponse?>()
    val newsState: LiveData<NewsResponse?> = _newsState

    private val _errorState = MutableLiveData<String?>()
    val errorState: LiveData<String?> = _errorState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val repository = NewsDataStore()

    fun fetchNews() {
        _isLoading.value = true
        repository.getNews(object : NewsCallback {
            override fun onLoading() {
                _isLoading.value = true
            }

            override fun onSuccess(newsResponse: NewsResponse?) {
                _isLoading.value = false
                _newsState.value = newsResponse
                _errorState.value = null
            }

            override fun onError(errorMessage: String) {
                _isLoading.value = false
                _errorState.value = errorMessage
            }
        })
    }
}