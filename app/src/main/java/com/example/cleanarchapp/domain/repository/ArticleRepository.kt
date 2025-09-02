package com.example.cleanarchapp.domain.repository

import com.example.cleanarchapp.domain.model.Article
import kotlinx.coroutines.flow.Flow


interface ArticleRepository {
    suspend fun get(): Flow<Result<List<Article>>>
}