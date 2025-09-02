package com.example.cleanarchapp.data.mapper

import com.example.cleanarchapp.data.network.dto.ArticleDto
import com.example.cleanarchapp.data.network.dto.NewsDto
import com.example.cleanarchapp.data.network.dto.SourceDto
import com.example.cleanarchapp.domain.model.Article
import com.example.cleanarchapp.domain.model.News
import com.example.cleanarchapp.domain.model.Source

fun SourceDto.toDomain(): Source =
    Source(
        name = name
    )

fun ArticleDto.toDomain(): Article =
    Article(
        source = source.toDomain(),
        author = author,
        title = title,
        description = description,
        url = url,
        imageUrl = urlToImage,
        publishedAt = publishedAt.split("T")[0]
    )

fun NewsDto.toDomain(): News =
    News(
        articles = articles.map { it.toDomain() }
    )
