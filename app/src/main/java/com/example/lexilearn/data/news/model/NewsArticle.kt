package com.example.lexilearn.data.news.model

import com.google.gson.Gson

data class NewsArticle(
    val source: NewsSource?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)

val gson = Gson()

fun serializeArticle(article: NewsArticle): String {
    return gson.toJson(article)
}

fun deserializeArticle(articleJson: String): NewsArticle {
    return gson.fromJson(articleJson, NewsArticle::class.java)
}