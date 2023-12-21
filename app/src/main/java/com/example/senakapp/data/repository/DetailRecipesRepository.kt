package com.example.senakapp.data.repository

import com.example.senakapp.data.retrofit.DetailRecipesService
import javax.inject.Inject

class DetailRecipesRepository @Inject constructor(private val detailRecipesService: DetailRecipesService){

    suspend fun getDetailRecipes(id: Int) = detailRecipesService.getDetailRecipes(id)
}