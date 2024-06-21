package com.example.lexilearn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lexilearn.data.news.NewsDataStore
import com.example.lexilearn.data.news.callback.NewsCallback
import com.example.lexilearn.data.news.model.NewsArticle
import com.example.lexilearn.data.news.model.NewsResponse

class SharedViewModel : ViewModel() {
    private val _selectedArticle = MutableLiveData<NewsArticle>()
    val selectedArticle: LiveData<NewsArticle> = _selectedArticle

    fun selectArticle(article: NewsArticle) {
        _selectedArticle.value = article
    }

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