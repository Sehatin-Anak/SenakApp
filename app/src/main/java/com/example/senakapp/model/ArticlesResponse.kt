package com.example.senakapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


data class ArticlesResponse(

	@Json(name="data")
	val data: List<DataItemArticles>
)

@Parcelize
data class DataItemArticles(

	@Json(name="createdAt")
	val createdAt: String,

	@Json(name="author")
	val author: List<AuthorItem>,

	@Json(name="id")
	val id: Int,

	@Json(name="title")
	val title: String,

	@Json(name="publicationDate")
	val publicationDate: String,

	@Json(name="url")
	val url: String,

	@Json(name="content")
	val content: String,

	@Json(name="updatedAt")
	val updatedAt: String
) : Parcelable


@Parcelize
data class AuthorItem(

	@Json(name="name")
	val name: String,

	@Json(name="articleId")
	val articleId: Int,

	@Json(name="id")
	val id: Int
): Parcelable
