package com.example.senakapp.ui.screen.home

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.senakapp.data.repository.HomeRepository
import com.example.senakapp.data.retrofit.service.BiodataService
import com.example.senakapp.data.retrofit.service.HomeService
import com.example.senakapp.model.FoodRecommendationsResponse
import com.example.senakapp.model.biodata.VerifyChildResponse
import com.example.senakapp.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val sharedPreferences: SharedPreferences, private val biodataService: BiodataService, private val homeService: HomeService, private val homeRepository: HomeRepository): ViewModel(){

companion object
{
    const val PAGE_SIZE = 7
}

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun deleteToken() {
        viewModelScope.launch {
            sharedPreferences.edit().remove("token").apply()
        }
    }
    fun saveTokenAsync(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    private val _foodRecommendations = MutableStateFlow<ApiResponse<FoodRecommendationsResponse>>(ApiResponse.Empty)
    val foodRecommendations: StateFlow<ApiResponse<FoodRecommendationsResponse>> get() = _foodRecommendations

    private val _verifyChildResult =
        MutableStateFlow<ApiResponse<VerifyChildResponse>>(ApiResponse.Empty)
    val verifyChildResult: StateFlow<ApiResponse<VerifyChildResponse>> get() = _verifyChildResult



    fun getFoodRecommendations(userId: String, pagination: Int) {
        viewModelScope.launch {
            _foodRecommendations.value = ApiResponse.Loading
            try {
                val result = homeRepository.getFoodRecommendations(userId, pagination ?: PAGE_SIZE)
                _foodRecommendations.value = result
            } catch (e: Exception) {
                _foodRecommendations.value = ApiResponse.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun verifyChild(userId: String) {
        viewModelScope.launch {
            try {
                val response = biodataService.getBioChild(userId)

                if (response.isSuccessful) {
                    val verifyChildResponse = response.body()

                    if (verifyChildResponse != null) {
                        // Lakukan sesuatu dengan data verifyChildResponse
                        _verifyChildResult.value = ApiResponse.Success(verifyChildResponse)
                    } else {
                        // Handle kasus response data=null
                        _verifyChildResult.value = ApiResponse.Error("Data is null")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("AuthViewModel", "verifyChild error: ${response.code()}, $errorBody")
                    // Handle error dari response
                    // Misalnya, response.code(), errorBody, dll.
                    _verifyChildResult.value = ApiResponse.Error(errorBody ?: "Unknown error")
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "verifyChild exception: ${e.message}")
                // Handle exception
                _verifyChildResult.value = ApiResponse.Error(e.message ?: "Unknown error")
            } finally {
                // Pastikan untuk menangani ApiResponse.Empty dengan benar
                if (_verifyChildResult.value is ApiResponse.Empty) {
                    _verifyChildResult.value = ApiResponse.Error("Unexpected empty response")
                }
            }
        }
    }



    fun getIdUser(): String? {
        return sharedPreferences.getString("idUser", null)
    }




}