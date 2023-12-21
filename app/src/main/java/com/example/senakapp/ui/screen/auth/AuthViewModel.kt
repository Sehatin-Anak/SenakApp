package com.example.senakapp.ui.screen.auth

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.senakapp.data.retrofit.AuthService
import com.example.senakapp.data.retrofit.BiodataService
import com.example.senakapp.model.auth.LoginResponse
import com.example.senakapp.model.biodata.VerifyChildResponse
import com.example.senakapp.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authService: AuthService,
    private val biodataService: BiodataService,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {



    fun saveTokenAsync(token: String) {
        viewModelScope.launch {
            sharedPreferences.edit().putString("token", token).apply()
        }
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    private val _loginResult = MutableStateFlow<ApiResponse<LoginResponse>>(ApiResponse.Loading)
    val loginResult: StateFlow<ApiResponse<LoginResponse>> = _loginResult

    private val _verifyChildResult =
        MutableStateFlow<ApiResponse<VerifyChildResponse>>(ApiResponse.Empty)
    val verifyChildResult: StateFlow<ApiResponse<VerifyChildResponse>> get() = _verifyChildResult
    fun performGoogleLogin(token: String) {
        viewModelScope.launch {
            try {
                val response = authService.authenticateWithGoogle(token)

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    Log.d("AuthViewModel", "performGoogleLogin: $loginResponse")
                    saveTokenAsync(token) // Simpan token setelah login berhasil
                    saveIdUserAsync(loginResponse!!.user.id)

                    // Lakukan sesuatu dengan data loginResponse
                    _loginResult.value = ApiResponse.Success(loginResponse)
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("AuthViewModel", "performGoogleLogin error: ${response.code()}, $errorBody")
                    // Handle error dari response
                    // Misalnya, response.code(), errorBody, dll.
                    _loginResult.value = ApiResponse.Error(errorBody ?: "Unknown error")
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "performGoogleLogin exception: ${e.message}")
                // Handle exception
                _loginResult.value = ApiResponse.Error(e.message ?: "Unknown error")
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

    fun saveIdUserAsync(idUser: String) {

            sharedPreferences.edit().putString("idUser", idUser).apply()

    }

    fun getIdUser(): String? {
        return sharedPreferences.getString("idUser", null)
    }







}
