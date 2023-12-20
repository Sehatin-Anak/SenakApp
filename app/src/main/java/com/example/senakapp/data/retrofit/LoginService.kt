package com.example.senakapp.data.retrofit

import com.example.senakapp.model.auth.LoginResponse
import retrofit2.http.GET

interface LoginService {


    @GET("auth/google")
    suspend fun googleLogin(): LoginResponse
}