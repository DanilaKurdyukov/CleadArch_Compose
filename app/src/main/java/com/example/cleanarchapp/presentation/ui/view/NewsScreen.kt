package com.example.cleanarchapp.presentation.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cleanarchapp.presentation.vm.ArticleViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewsScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState
) {
    val articleViewModel = hiltViewModel<ArticleViewModel>()
    NewsList(modifier, articleViewModel, snackbarHostState)
}

@Composable
fun NewsList(modifier: Modifier, articleViewModel: ArticleViewModel, snackbarHostState: SnackbarHostState) {
    LaunchedEffect(
        key1 = articleViewModel
    ) {
        articleViewModel.getArticles()
    }
    val articles = articleViewModel.articles.collectAsStateWithLifecycle()
    LaunchedEffect(
        key1 = articleViewModel.error
    ) {
        articleViewModel.error.collectLatest { message ->
            snackbarHostState.showSnackbar(message = message)
        }
    }
    LazyColumn(
        modifier = modifier.padding(top = 10.dp)
    ) {
        items(items = articles.value) { article ->
            CardItem(article)
        }
    }
}