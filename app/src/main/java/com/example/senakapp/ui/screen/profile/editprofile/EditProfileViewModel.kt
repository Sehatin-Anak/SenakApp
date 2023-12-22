package com.example.senakapp.ui.screen.profile.editprofile

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.senakapp.data.retrofit.service.BiodataService
import com.example.senakapp.model.biodata.BiodataRequestResponse
import com.example.senakapp.model.biodata.UpdateBiodataRequest
import com.example.senakapp.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(private val biodataService: BiodataService, private val sharedPreferences: SharedPreferences) : ViewModel(){



    private val _updateBiodata =
        MutableStateFlow<ApiResponse<BiodataRequestResponse>>(ApiResponse.Empty)
    val updateBiodata: StateFlow<ApiResponse<BiodataRequestResponse>> get() = _updateBiodata

    fun getIdUser (): String? {
        return sharedPreferences.getString("idUser", null)
    }


    fun putBiodata(userId: String, updateBiodataRequest: UpdateBiodataRequest) {
        viewModelScope.launch {
            try {
                _updateBiodata.value = ApiResponse.Loading


                // Panggil fungsi postBioChild dari BiodataService
                val response = biodataService.putBioChild(userId, updateBiodataRequest)

                // Periksa apakah respons berhasil atau tidak
                if (response.isSuccessful) {
                    // Respons berhasil, simpan respons ke StateFlow
                    _updateBiodata.value = ApiResponse.Success(response.body()!!)
                    Log.d("BiodataViewModel", "updateBiodataSuccess: ${response.body()}")
                } else {
                    // Respons tidak berhasil, simpan pesan kesalahan ke StateFlow
                    _updateBiodata.value =
                        ApiResponse.Error("Gagal mengirim update: ${response.message()}")
                    Log.d("BiodataViewModel", "updateBiodataError: ${response.message()}")
                }
            } catch (e: Exception) {
                // Tangani kesalahan yang mungkin terjadi selama proses
                // Simpan pesan kesalahan ke StateFlow
                _updateBiodata.value = ApiResponse.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }

}