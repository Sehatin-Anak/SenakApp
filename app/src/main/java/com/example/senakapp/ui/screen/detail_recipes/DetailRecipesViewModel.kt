package com.example.senakapp.ui.screen.detail_recipes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.senakapp.data.repository.DetailRecipesRepository
import com.example.senakapp.data.retrofit.DetailRecipesService
import com.example.senakapp.model.detailrecipes.DetailRecipesResponse
import com.example.senakapp.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailRecipesViewModel @Inject constructor(
    private val detailRecipesService: DetailRecipesService
): ViewModel() {
    private val _detailRecipesResult = MutableStateFlow<ApiResponse<DetailRecipesResponse>>(ApiResponse.Empty)
    val detailRecipesResult: StateFlow<ApiResponse<DetailRecipesResponse>> get() = _detailRecipesResult
    fun getDetailRecipes(id: Int) {
        viewModelScope.launch {
            try {
                // Menetapkan ApiResponse.Loading untuk menunjukkan proses pengambilan data sedang berlangsung
                _detailRecipesResult.value = ApiResponse.Loading

                // Memanggil fungsi repository untuk mendapatkan data detail resep
                val response = detailRecipesService.getDetailRecipes(id)

                // Mengambil data dari response.body()
                val detailRecipesResponse = response.body()

                // Memeriksa apakah response.body() tidak null
                if (response.isSuccessful && detailRecipesResponse != null) {
                    // Jika pengambilan data berhasil, set ApiResponse.Success
                    _detailRecipesResult.value = ApiResponse.Success(detailRecipesResponse)
                } else {
                    // Jika terjadi kesalahan dalam response, set ApiResponse.Error
                    _detailRecipesResult.value = ApiResponse.Error("Failed to fetch data")
                }
            } catch (e: Exception) {
                // Jika terjadi kesalahan lainnya, set ApiResponse.Error
                _detailRecipesResult.value = ApiResponse.Error(e.message ?: "Unknown error")
                Log.d("DetailRecipesViewModel", "getDetailRecipes: ${e.message}")
            }
        }
    }
}