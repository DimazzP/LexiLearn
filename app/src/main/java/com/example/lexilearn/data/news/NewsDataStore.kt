package com.example.lexilearn.data.news

import android.util.Log
import com.example.lexilearn.data.news.apiconfig.NewsConfig
import com.example.lexilearn.data.news.callback.NewsCallback
import com.example.lexilearn.data.news.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsDataStore: NewsRepository {
    override fun getNews(callback: NewsCallback) {
        callback.onLoading()

        val client = NewsConfig.getNewsService().getNewsData()
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onSuccess(response.body())
                } else {
                    callback.onError("Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                callback.onError("Failure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        private const val TAG = "NewsDataStore"
    }
}