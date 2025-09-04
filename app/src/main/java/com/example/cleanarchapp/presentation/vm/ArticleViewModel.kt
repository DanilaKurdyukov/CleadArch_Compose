package com.example.cleanarchapp.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchapp.domain.model.Article
import com.example.cleanarchapp.domain.network.NetworkMonitor
import com.example.cleanarchapp.domain.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository: ArticleRepository,
    private val networkMonitor: NetworkMonitor
): ViewModel() {

    private val _articles = MutableStateFlow(value = emptyList<Article>())
    val articles = _articles.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    init {
        observeNetwork()
    }

    fun getArticles() {
        viewModelScope.launch {
            repository.get().collectLatest { result ->
                result.onSuccess {
                    data -> _articles.value = data.toList()
                }.onFailure { data ->  _error.emit( value = data?.message ?: "Unknown error")  }
            }
        }
    }

    private fun observeNetwork() {
        viewModelScope.launch {
            networkMonitor.isConnected.collectLatest { connected ->
                if (connected) {
                    getArticles() // например, повторить запрос
                }
            }
        }
    }
}