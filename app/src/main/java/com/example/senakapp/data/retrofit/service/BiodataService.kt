package com.example.senakapp.data.retrofit.service

import com.example.senakapp.model.biodata.BiodataRequest
import com.example.senakapp.model.biodata.BiodataRequestResponse
import com.example.senakapp.model.biodata.BiodataResponse
import com.example.senakapp.model.biodata.UpdateBiodataRequest
import com.example.senakapp.model.biodata.VerifyChildResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BiodataService {

    @GET("/user/{id}/bioChild")
    suspend fun getBioChild(@Path("id") userId: String): Response<VerifyChildResponse>


    @POST("/user/{id}/bioChild")
    suspend fun postBioChild(
        @Path("id") userId: String, // Ganti dengan tipe data yang sesuai
        @Body biodataRequest: BiodataRequest
    ): Response<BiodataRequestResponse> // Ganti dengan tipe data respons yang sesuai


    @PUT("/user/:id/bioChild")
    suspend fun putBioChild(
        @Path("id") userId: String,
        @Body updateBiodataRequest: UpdateBiodataRequest

    ): Response<BiodataRequestResponse>




}