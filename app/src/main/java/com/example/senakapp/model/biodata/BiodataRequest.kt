package com.example.senakapp.model.biodata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BiodataRequest(

	@Json(name="name")
	val name: String,

	@Json(name="weight")
	val weight: Int,

	@Json(name="tall")
	val tall: Int,

	@Json(name="ageCategory")
	val ageCategory: Int,

	@Json(name="age")
	val age: Int
)


data class UpdateBiodataRequest(
	@Json(name = "age")
	val age: Int,

	@Json(name = "ageCategory")
	val ageCategory: Int,

)

