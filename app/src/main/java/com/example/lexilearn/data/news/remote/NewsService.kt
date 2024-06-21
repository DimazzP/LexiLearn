package com.example.lexilearn.data.news.remote

import com.example.lexilearn.data.news.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {
    @GET("v2/everything?q=dyslexia")
    fun getNewsData(): Call<NewsResponse>
}