package com.example.senakapp.data.retrofit.service

import com.example.senakapp.model.FoodRecommendationsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService{

    @POST("/user/{userId}/dashboard/foodRecomend")
    suspend fun getFoodRecommendations(
        @Path("userId") userId: String,
        @Query("pagination") pagination: Int,

    ): Response<FoodRecommendationsResponse>

}