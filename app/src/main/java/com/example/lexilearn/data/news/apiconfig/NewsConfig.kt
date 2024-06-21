package com.example.lexilearn.data.news.apiconfig

import com.example.lexilearn.BuildConfig
import com.example.lexilearn.data.news.remote.NewsService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsConfig {
    companion object {
        fun getNewsService(): NewsService {
            val apiKey = BuildConfig.API_KEY
            val baseUrl = BuildConfig.BASE_URL
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", apiKey)
                    .build()
                chain.proceed(requestHeaders)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(NewsService::class.java)
        }
    }
}