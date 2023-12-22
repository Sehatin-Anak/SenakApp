package com.example.senakapp.ui.screen.profile

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(private val sharedPreferences: SharedPreferences): ViewModel() {

    fun deleteToken() {
        sharedPreferences.edit().remove("token").apply()
    }

   fun deleteIdUser() {
              sharedPreferences.edit().remove("idUser").apply()
   }
}