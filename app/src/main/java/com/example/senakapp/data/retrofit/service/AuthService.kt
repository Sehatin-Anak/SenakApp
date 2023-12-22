package com.example.senakapp.data.retrofit.service

import com.example.senakapp.model.auth.LoginResponse
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/google")
    suspend fun authenticateWithGoogle(
        @Header("idToken")
        token: String):
            Response<LoginResponse>
}