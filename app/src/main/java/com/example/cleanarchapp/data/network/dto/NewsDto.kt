package com.example.cleanarchapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName(value = "status")
    val status: String,
    @SerializedName(value = "totalResults")
    val totalResults: Int,
    @SerializedName(value = "articles")
    val articles: List<ArticleDto>
)
