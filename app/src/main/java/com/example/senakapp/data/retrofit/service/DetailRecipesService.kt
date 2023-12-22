package com.example.senakapp.data.retrofit.service

import com.example.senakapp.model.detailrecipes.DetailRecipesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailRecipesService {

    @GET("/dashboard/foodRecomend/{id}")
    suspend fun getDetailRecipes(
        @Path("id") id: Int
    ): Response<DetailRecipesResponse>
}