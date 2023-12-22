package com.example.senakapp.ui.screen.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.senakapp.data.repository.ArticleRepository

import com.example.senakapp.model.ArticlesResponse
import com.example.senakapp.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class ArticlesViewModel @Inject constructor(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _articles = MutableStateFlow<ApiResponse<ArticlesResponse>>(ApiResponse.Empty)
    val articles: StateFlow<ApiResponse<ArticlesResponse>> get() = _articles

    fun getArticles() {
        viewModelScope.launch {
            _articles.value = ApiResponse.Loading
            try {
                val response = articleRepository.getArticles()

                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        _articles.value = ApiResponse.Success(result)
                    } else {
                        _articles.value = ApiResponse.Error("Response body is null")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    _articles.value = ApiResponse.Error(errorBody ?: "Unknown error")
                }
            } catch (e: Exception) {
                _articles.value = ApiResponse.Error(e.message ?: "Unknown error")
            }
        }
    }
}
