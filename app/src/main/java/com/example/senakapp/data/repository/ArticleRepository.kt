package com.example.senakapp.data.repository

import com.example.senakapp.data.retrofit.service.ArticleService
import javax.inject.Inject

class ArticleRepository @Inject constructor(private val articleService: ArticleService) {

    suspend fun getArticles() = articleService.getArticles()
}