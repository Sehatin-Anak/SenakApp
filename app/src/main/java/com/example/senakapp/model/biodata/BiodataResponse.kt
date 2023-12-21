package com.example.senakapp.model.biodata

import com.example.senakapp.model.DataItem
import com.squareup.moshi.Json

data class BiodataResponse(

	@Json(name="data")
	val data: DataItem
)

data class DataItem(

	@Json(name="createdAt")
	val createdAt: String,

	@Json(name="name")
	val name: String,

	@Json(name="weight")
	val weight: Int,

	@Json(name="id")
	val id: Int,

	@Json(name="tall")
	val tall: Int,

	@Json(name="ageCategory")
	val ageCategory: Int,

	@Json(name="userId")
	val userId: String,

	@Json(name="age")
	val age: Int,

	@Json(name="updatedAt")
	val updatedAt: String
)
