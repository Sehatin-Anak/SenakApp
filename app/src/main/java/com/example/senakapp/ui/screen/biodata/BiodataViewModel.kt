package com.example.senakapp.ui.screen.biodata

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.senakapp.data.retrofit.BiodataService
import com.example.senakapp.model.biodata.BiodataRequest
import com.example.senakapp.model.biodata.BiodataRequestResponse
import com.example.senakapp.model.biodata.BiodataResponse
import com.example.senakapp.model.biodata.VerifyChildResponse
import com.example.senakapp.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    fun verifyChild(userId: String) {
        viewModelScope.launch {
            try {
                _verifyChildResult.value = ApiResponse.Loading

                // Panggil fungsi getBioChild dari BiodataService
                val response = biodataService.getBioChild(userId)

                // Periksa apakah respons berhasil atau tidak
                if (response.isSuccessful) {
                    // Respons berhasil, simpan respons ke StateFlow
                    _verifyChildResult.value = ApiResponse.Success(response.body()!!)
                    Log.d("BiodataViewModel", "verifyChild: Success")
                    // Lakukan tindakan sukses jika diperlukan
                } else {
                    // Respons tidak berhasil, simpan pesan kesalahan ke StateFlow
                    _verifyChildResult.value =
                        ApiResponse.Error("Gagal verifikasi anak: ${response.message()}")
                }
            } catch (e: Exception) {
                // Tangani kesalahan yang mungkin terjadi selama proses
                // Simpan pesan kesalahan ke StateFlow
                _verifyChildResult.value =
                    ApiResponse.Error("Terjadi kesalahan verifikasi anak: ${e.message}")
            }
        }
    }

    fun getIdUser(): String? {
        return sharedPreferences.getString("idUser", null)
    }
}