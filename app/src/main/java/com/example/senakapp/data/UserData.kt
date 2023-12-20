package com.example.senakapp.data

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val uuid: String?,
    val displayName: String,
    val email: String,
    val idToken: String,
    val photoUrl: Uri,

): Parcelable
