package com.example.cleanarchapp.presentation.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cleanarchapp.domain.model.Article
import com.example.cleanarchapp.presentation.vm.ArticleViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewsScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState
) {
    val articleViewModel = hiltViewModel<ArticleViewModel>()

    NewsList(
        modifier = modifier,
        articleViewModel = articleViewModel,
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun NewsList(
    modifier: Modifier,
    articleViewModel: ArticleViewModel,
    snackbarHostState: SnackbarHostState,
) {
    LaunchedEffect(
        key1 = articleViewModel
    ) {
        articleViewModel.getArticles()
    }
    val articles = articleViewModel.filteredArticles.collectAsStateWithLifecycle(initialValue = emptyList<Article>())
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