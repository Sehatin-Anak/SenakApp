package com.example.senakapp.data.repository

import com.example.senakapp.data.retrofit.service.HomeService
import com.example.senakapp.model.FoodRecommendationsResponse
import com.example.senakapp.utils.ApiResponse
import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeService: HomeService) {

    suspend fun getFoodRecommendations(userId: String, pagination: Int): ApiResponse<FoodRecommendationsResponse> {
        try {
            val response = homeService.getFoodRecommendations(userId, pagination)
            if (response.isSuccessful) {
                return ApiResponse.Success(response.body()!!)
            }
            return ApiResponse.Error(
                "${response.code()}, ${response.errorBody()?.string()}"
            )
        } catch (e: Exception) {
            return ApiResponse.Error(e.message ?: "Unknown error")
        }
    }
}