package com.example.senakapp.data.retrofit.service

import com.example.senakapp.model.FoodRecommendationsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/dashboard/search")
    fun search(@Query("search") search: String): Response<FoodRecommendationsResponse>
}