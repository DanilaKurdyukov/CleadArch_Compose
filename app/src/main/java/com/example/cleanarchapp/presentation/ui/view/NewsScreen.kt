package com.example.cleanarchapp.presentation.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cleanarchapp.presentation.vm.ArticleViewModel

@Composable
fun NewsScreen(modifier: Modifier) {
    val articleViewModel = hiltViewModel<ArticleViewModel>()
    NewsList(modifier, articleViewModel)
}

@Composable
fun NewsList(modifier: Modifier, articleViewModel: ArticleViewModel) {
    LaunchedEffect(
        key1 = true
    ) {
        articleViewModel.getArticles()
    }
    val articles = articleViewModel.articles.collectAsStateWithLifecycle()
    val error = articleViewModel.error.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = modifier.padding(top = 10.dp)
    ) {
        items(items = articles.value) { article ->
            CardItem(article)
        }
    }
}