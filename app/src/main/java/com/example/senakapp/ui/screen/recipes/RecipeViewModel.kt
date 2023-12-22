package com.example.senakapp.ui.screen.recipes

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.senakapp.data.repository.HomeRepository
import com.example.senakapp.data.retrofit.service.SearchService
import com.example.senakapp.model.FoodRecommendationsResponse
import com.example.senakapp.ui.screen.home.HomeViewModel
import com.example.senakapp.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel @Inject constructor(private val searchService: SearchService, private val homeRepository: HomeRepository, private val sharedPreferences: SharedPreferences): ViewModel(){

    fun getIdUser(): String? {
        return sharedPreferences.getString("idUser", null)
    }
    private val _foodRecommendations = MutableStateFlow<ApiResponse<FoodRecommendationsResponse>>(ApiResponse.Empty)
    val foodRecommendations: StateFlow<ApiResponse<FoodRecommendationsResponse>> get() = _foodRecommendations


    fun getFoodRecommendations(userId: String, pagination: Int) {
        viewModelScope.launch {
            _foodRecommendations.value = ApiResponse.Loading
            try {
                val result = homeRepository.getFoodRecommendations(userId, pagination ?: HomeViewModel.PAGE_SIZE)
                _foodRecommendations.value = result
            } catch (e: Exception) {
                _foodRecommendations.value = ApiResponse.Error(e.message ?: "Unknown error")
            }
        }
    }








}