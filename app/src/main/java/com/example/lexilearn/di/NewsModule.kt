package com.example.lexilearn.di

import com.example.lexilearn.BuildConfig
import com.example.lexilearn.data.lib.HeaderInterceptor
import com.example.lexilearn.data.news.remote.NewsService
import com.example.lexilearn.util.PreferenceManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val newsModule = module {
    single {
        return@single OkHttpClient.Builder()
            .addInterceptor(getHeaderInterceptor(get()))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { provideNewsService(get()) }
}
private fun getHeaderInterceptor(preferenceManager: PreferenceManager): Interceptor {
    val headers = HashMap<String, String>()
    headers["Authorization"] = BuildConfig.API_KEY

    return HeaderInterceptor(headers, preferenceManager)
}

@Override
fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

