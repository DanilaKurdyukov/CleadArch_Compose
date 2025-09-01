package com.example.cleanarchapp.domain.model

import com.google.gson.annotations.SerializedName

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String
)