package com.example.cleanarchapp.data.repository

import com.example.cleanarchapp.data.mapper.toDomain
import com.example.cleanarchapp.data.network.ApiService
import com.example.cleanarchapp.domain.model.Article
import com.example.cleanarchapp.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class ArticleRepositoryImlp @Inject constructor(
    private val apiService: ApiService
): ArticleRepository {

    override suspend fun get(): Result<Flow<List<Article>>> {
        return try {
            val response = flow {
                val data = apiService.getNews(country = "us",apiKey = "84c0e6086e184731bfa47302fad0ab65")
                emit(value = data.articles.map { it.toDomain() })
            }
            Result.success(value = response)
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}