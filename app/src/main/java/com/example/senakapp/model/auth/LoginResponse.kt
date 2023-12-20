package com.example.senakapp.model.auth

import com.squareup.moshi.Json

data class LoginResponse(

	@Json(name="message")
	val message: String,

	@Json(name="user")
	val user: User
)

data class User(

	@Json(name="createdAt")
	val createdAt: String,

	@Json(name="img")
	val img: String,

	@Json(name="name")
	val name: String,

	@Json(name="id")
	val id: String,

	@Json(name="email")
	val email: String,

	@Json(name="updatedAt")
	val updatedAt: String
)
