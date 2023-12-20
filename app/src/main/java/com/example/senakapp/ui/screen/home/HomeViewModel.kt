package com.example.senakapp.ui.screen.home

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val sharedPreferences: SharedPreferences): ViewModel(){

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }


}