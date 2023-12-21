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
    suspend fun performGoogleLogin(token: String) {

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



    fun saveIdUserAsync(idUser: String) {

            sharedPreferences.edit().putString("idUser", idUser).apply()

    }

    fun getIdUser(): String? {
        return sharedPreferences.getString("idUser", null)
    }







}
