package com.example.senakapp.data.retrofit.service

import com.example.senakapp.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ArticleService {

    @GET("/dashboard/article")
    suspend fun getArticles(): Response<ArticlesResponse>
}