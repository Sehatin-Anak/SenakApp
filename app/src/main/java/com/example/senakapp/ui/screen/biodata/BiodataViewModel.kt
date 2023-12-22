package com.example.senakapp.ui.screen.biodata

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.senakapp.data.retrofit.service.BiodataService
import com.example.senakapp.model.biodata.BiodataRequest
import com.example.senakapp.model.biodata.BiodataRequestResponse
import com.example.senakapp.model.biodata.VerifyChildResponse
import com.example.senakapp.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BiodataViewModel @Inject constructor(private val sharedPreferences: SharedPreferences,private val biodataService: BiodataService) : ViewModel() {

    private val _postBiodataResult =
        MutableStateFlow<ApiResponse<BiodataRequestResponse>>(ApiResponse.Empty)
    val postBiodataResult: StateFlow<ApiResponse<BiodataRequestResponse>> get() = _postBiodataResult


    private val _verifyChildResult =
        MutableStateFlow<ApiResponse<VerifyChildResponse>>(ApiResponse.Empty)
    val verifyChildResult: StateFlow<ApiResponse<VerifyChildResponse>> get() = _verifyChildResult
    fun postBiodata(userId: String, biodataRequest: BiodataRequest) {
        viewModelScope.launch {
            try {
                _postBiodataResult.value = ApiResponse.Loading

                // Panggil fungsi postBioChild dari BiodataService
                val response = biodataService.postBioChild(userId, biodataRequest)

                // Periksa apakah respons berhasil atau tidak
                if (response.isSuccessful) {
                    // Respons berhasil, simpan respons ke StateFlow
                    _postBiodataResult.value = ApiResponse.Success(response.body()!!)
                    Log.d("BiodataViewModel", "postBiodata: ${response.body()}")
                } else {
                    // Respons tidak berhasil, simpan pesan kesalahan ke StateFlow
                    _postBiodataResult.value =
                        ApiResponse.Error("Gagal mengirim biodata: ${response.message()}")
                }
            } catch (e: Exception) {
                // Tangani kesalahan yang mungkin terjadi selama proses
                // Simpan pesan kesalahan ke StateFlow
                _postBiodataResult.value = ApiResponse.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }

    suspend fun verifyChild(userId: String) {

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

    fun getIdUser(): String? {
        return sharedPreferences.getString("idUser", null)
    }
}