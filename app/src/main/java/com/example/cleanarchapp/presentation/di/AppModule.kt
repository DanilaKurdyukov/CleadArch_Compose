package com.example.cleanarchapp.presentation.di

import com.example.cleanarchapp.data.network.ApiService
import com.example.cleanarchapp.data.repository.ArticleRepositoryImlp
import com.example.cleanarchapp.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideArticleRepository(apiService: ApiService): ArticleRepository =
        ArticleRepositoryImlp(apiService)
}