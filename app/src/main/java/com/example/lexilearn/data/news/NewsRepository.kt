package com.example.lexilearn.data.news

import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.data.news.callback.NewsCallback
import com.example.lexilearn.data.news.model.NewsResponse
import com.example.lexilearn.domain.quiz.model.QuizModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface NewsRepository {
    fun getNews(callback: NewsCallback)
}