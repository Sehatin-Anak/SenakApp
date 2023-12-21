package com.example.senakapp.data.repository

import com.example.senakapp.data.retrofit.AuthService
import com.example.senakapp.model.auth.LoginResponse
import com.example.senakapp.utils.ApiResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(val authService: AuthService){

}