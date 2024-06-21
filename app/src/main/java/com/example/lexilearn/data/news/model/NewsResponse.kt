package com.example.lexilearn.data.news.model

data class NewsResponse(
    val status: String?,
    val totalResults: Int?,
    val articles: List<NewsArticle>?
)