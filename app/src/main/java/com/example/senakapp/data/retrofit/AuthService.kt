package com.example.senakapp.data.retrofit

import com.example.senakapp.model.auth.LoginResponse
import com.example.senakapp.utils.ApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthService {
    @GET("/auth/google")
    suspend fun authenticateWithGoogle(
        @Header("idToken")
        token: String):
            Response<LoginResponse>
}