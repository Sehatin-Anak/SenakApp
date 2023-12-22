package com.example.senakapp.model.search

import com.squareup.moshi.Json

data class SearchResponse(

	@Json(name="data")
	val data: Data
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

data class AuthorItem(

	@Json(name="name")
	val name: String,

	@Json(name="articleId")
	val articleId: Int,

	@Json(name="id")
	val id: Int
)

data class IngredientsItem(

	@Json(name="ingredient")
	val ingredient: String,

	@Json(name="foodRecomId")
	val foodRecomId: Int,

	@Json(name="id")
	val id: Int
)

data class ArticlesItem(

	@Json(name="score")
	val score: Any,

	@Json(name="item")
	val item: Item,

	@Json(name="refIndex")
	val refIndex: Int
)

data class Data(

	@Json(name="foodRecom")
	val foodRecom: List<FoodRecomItem>,

	@Json(name="articles")
	val articles: List<ArticlesItem>
)

data class Item(

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
	val agregateRating: Int,

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
	val updatedAt: String,

	@Json(name="author")
	val author: List<AuthorItem>,

	@Json(name="title")
	val title: String,

	@Json(name="publicationDate")
	val publicationDate: String,

	@Json(name="url")
	val url: String,

	@Json(name="content")
	val content: String
)

data class FoodRecomItem(

	@Json(name="score")
	val score: Any,

	@Json(name="item")
	val item: Item,

	@Json(name="refIndex")
	val refIndex: Int
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
