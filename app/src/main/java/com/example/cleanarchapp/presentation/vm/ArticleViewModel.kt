package com.example.cleanarchapp.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchapp.domain.model.Article
import com.example.cleanarchapp.domain.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository: ArticleRepository
): ViewModel() {

    private val _articles = MutableStateFlow(value = emptyList<Article>())
    val articles = _articles.asStateFlow()

    private val _error = MutableStateFlow(value = "")
    val error = _error.asStateFlow()

    fun getArticles() {
        viewModelScope.launch {
            repository.get().collectLatest { result ->
                result.onSuccess {
                    data -> _articles.value = data.toList()
                }.onFailure { data ->  _error.value = data?.message ?: "Unknown error"}
            }
        }
    }

}