package com.example.lexilearn.di

import com.example.lexilearn.data.auth.remote.AuthService
import com.example.lexilearn.data.lib.HeaderInterceptor
import com.example.lexilearn.data.screening.remote.ScreeningService
import com.example.lexilearn.util.ConstVar
import com.example.lexilearn.util.PreferenceManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
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
            .baseUrl(ConstVar.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { provideAuthService(get()) }
    single { provideScreeningService(get()) }
}

private fun getHeaderInterceptor(preferenceManager: PreferenceManager): Interceptor {
    val headers = HashMap<String, String>()
    headers["Content-Type"] = "application/json"

    return HeaderInterceptor(headers, preferenceManager)
}

private fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)
private fun provideScreeningService(retrofit: Retrofit): ScreeningService = retrofit.create(ScreeningService::class.java)