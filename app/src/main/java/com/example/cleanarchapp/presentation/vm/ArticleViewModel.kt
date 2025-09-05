package com.example.cleanarchapp.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchapp.domain.model.Article
import com.example.cleanarchapp.domain.network.NetworkMonitor
import com.example.cleanarchapp.domain.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
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
                if (connected && _articles.value.isEmpty()) {
                    getArticles()
                }
            }
        }
    }

    //for search
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    val filteredArticles =  searchText
        .combine(_articles) { query, list ->
            if (query.isBlank()) list
            else list.filter { it.title!!.contains(other = query, ignoreCase = true) }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _articles.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun setSearching(value: Boolean) {
        _isSearching.value = value
        if (!value) _searchText.value = ""
    }



}