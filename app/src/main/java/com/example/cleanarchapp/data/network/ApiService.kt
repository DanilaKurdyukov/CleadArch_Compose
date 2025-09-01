package com.example.cleanarchapp.data.network

import com.example.cleanarchapp.data.network.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query(value = "country") country: String,
        @Query(value = "apiKey") apiKey: String
    ): NewsDto
}