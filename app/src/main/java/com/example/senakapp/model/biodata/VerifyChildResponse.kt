package com.example.senakapp.model.biodata

import com.squareup.moshi.Json

data class VerifyChildResponse(

	@Json(name="data")
	val data: Data? = null
)

data class Data(

	@Json(name="createdAt")
	val createdAt: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="weight")
	val weight: Int? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="tall")
	val tall: Int? = null,

	@Json(name="ageCategory")
	val ageCategory: Int? = null,

	@Json(name="userId")
	val userId: String? = null,

	@Json(name="age")
	val age: Int? = null,

	@Json(name="updatedAt")
	val updatedAt: String? = null
)
