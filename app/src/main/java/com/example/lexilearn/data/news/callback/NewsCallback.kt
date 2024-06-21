package com.example.lexilearn.data.news.callback

import com.example.lexilearn.data.news.model.NewsResponse

interface NewsCallback {
    fun onLoading()
    fun onSuccess(newsResponse: NewsResponse?)
    fun onError(errorMessage: String)
}