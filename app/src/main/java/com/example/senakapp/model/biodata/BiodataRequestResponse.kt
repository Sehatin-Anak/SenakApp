package com.example.senakapp.model.biodata

import com.squareup.moshi.Json

data class BiodataRequestResponse(

	@Json(name="data")
	val data: DataChild,

	@Json(name="message")
	val message: String
)

data class Child(

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

data class DataChild(

	@Json(name="child")
	val child: Child
)
