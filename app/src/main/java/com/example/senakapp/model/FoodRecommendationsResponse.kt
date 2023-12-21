package com.example.senakapp.model

import com.squareup.moshi.Json

data class FoodRecommendationsResponse(

	@Json(name="data")
	val data: List<DataItem>
)

data class NutritionInfo(

	@Json(name="carbohydrates")
	val carbohydrates: Any,

	@Json(name="saturatedFat")
	val saturatedFat: Any,

	@Json(name="sodium")
	val sodium: Any,

	@Json(name="fiber")
	val fiber: Any,

	@Json(name="protein")
	val protein: Any,

	@Json(name="fat")
	val fat: Any,

	@Json(name="cholesterol")
	val cholesterol: Any,

	@Json(name="id")
	val id: Int,

	@Json(name="calories")
	val calories: Any,

	@Json(name="sugar")
	val sugar: Any
)

data class IngredientsItem(

	@Json(name="ingredient")
	val ingredient: String,

	@Json(name="foodRecomId")
	val foodRecomId: Int,

	@Json(name="id")
	val id: Int
)

data class InstructionsItem(

	@Json(name="foodRecomId")
	val foodRecomId: Int,

	@Json(name="instruction")
	val instruction: String,

	@Json(name="stepOrder")
	val stepOrder: Int,

	@Json(name="id")
	val id: Int
)

data class DataItem(

	@Json(name="img")
	val img: String,

	@Json(name="Category")
	val category: String,

	@Json(name="nutritionInfoId")
	val nutritionInfoId: Int,

	@Json(name="Ingredients")
	val ingredients: List<IngredientsItem>,

	@Json(name="description")
	val description: String,

	@Json(name="Instructions")
	val instructions: List<InstructionsItem>,

	@Json(name="agregateRating")
	val agregateRating: Double,

	@Json(name="ageCategory")
	val ageCategory: Int,

	@Json(name="childId")
	val childId: Int,

	@Json(name="createdAt")
	val createdAt: String,

	@Json(name="nutritionInfo")
	val nutritionInfo: NutritionInfo,

	@Json(name="reviewCount")
	val reviewCount: Int,

	@Json(name="name")
	val name: String,

	@Json(name="id")
	val id: Int,

	@Json(name="updatedAt")
	val updatedAt: String
)
