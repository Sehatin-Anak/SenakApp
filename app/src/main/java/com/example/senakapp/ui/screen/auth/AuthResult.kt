package com.example.senakapp.ui.screen.auth


    sealed class AuthResult {
        object Success : AuthResult()
        data class Error(val message: String) : AuthResult()
    }
